
package com.omvp.app.ui.samples.list.presenter;


import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.base.reactivex.BaseDisposableCompletableObserver;
import com.omvp.app.base.reactivex.BaseDisposableMaybeObserver;
import com.omvp.app.model.SampleModel;
import com.omvp.app.model.mapper.SampleModelDataMapper;
import com.omvp.app.ui.samples.list.adapter.SampleListAdapter;
import com.omvp.app.ui.samples.list.view.SampleListView;
import com.omvp.app.util.RecyclerDragHelper;
import com.omvp.domain.SampleDomain;
import com.omvp.domain.interactor.GetSampleListUseCase;
import com.omvp.domain.interactor.RemoveSampleUseCase;
import com.omvp.domain.interactor.SaveSampleUseCase;

import org.threeten.bp.LocalDateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SampleListPresenterImpl extends BasePresenter<SampleListView>
        implements SampleListPresenter,
        SampleListAdapter.AdapterCallback,
        RecyclerDragHelper.ActionCompletionContract,
        SwipeRefreshLayout.OnRefreshListener {

    @Inject
    GetSampleListUseCase mGetSampleListUseCase;
    @Inject
    SaveSampleUseCase mSaveSampleUseCase;
    @Inject
    RemoveSampleUseCase mRemoveSampleUseCase;
    @Inject
    SampleModelDataMapper mSampleModelDataMapper;

    private List<SampleDomain> mSampleDomainList;

    @Inject
    public SampleListPresenterImpl(SampleListView sampleListView) {
        super(sampleListView);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();

        loadSampleList();
    }

    @Override
    public void sampleItemSelected(int position, View sharedView) {
        sampleItemSelected(mSampleDomainList.get(position), sharedView);
    }

    @Override
    public void sampleItemDeleteSelected(int position) {
        removeItem(position);
    }

    @Override
    public void onAddSampleItemSelected() {
        addItem();
    }

    @Override
    public void onViewMoved(int oldPosition, int newPosition) {
        Collections.swap(mSampleDomainList, oldPosition, newPosition);
        drawViewMoved(oldPosition, newPosition);
    }

    @Override
    public void onViewSwiped(int position) {
        mSampleDomainList.remove(position);
        drawViewSwiped(position);
    }

    @Override
    public void onRefresh() {
        /*
        *   This code edits a random item just to check the
        *   efficiency of the Adapter update by DiffUtils.
        */
        mDisposableManager.add(Maybe.just(mSampleDomainList)
                .map(new Function<List<SampleDomain>, List<SampleModel>>() {
                    @Override
                    public List<SampleModel> apply(List<SampleDomain> sampleDomains) throws Exception {
                        return mSampleModelDataMapper.transform(sampleDomains);
                    }
                })
                .map(new Function<List<SampleModel>, List<SampleModel>>() {
                    @Override
                    public List<SampleModel> apply(List<SampleModel> sampleModels) throws Exception {
                        return editRandomItem(sampleModels);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new BaseDisposableMaybeObserver<List<SampleModel>>(mContext) {
                    @Override
                    public void onSuccess(List<SampleModel> sampleModelList) {
                        hideProgress();
                        drawUpdatedItems(sampleModelList);
                    }

                    @Override
                    protected void onError(int code, String title, String description) {
                        hideProgress();
                        showError(code, title, description);
                    }

                    @Override
                    public void onComplete() {
                        hideProgress();
                        showEmptyView();
                    }
                }));
    }

    private List<SampleModel> editRandomItem(List<SampleModel> items) {
        int max = items.size() - 1;
        int min = 0;
        Random random = new Random();
        int index = random.nextInt(max - min + 1);

        SampleModel model = items.get(index);
        model.setTitle(String.valueOf(System.currentTimeMillis()));
        items.set(index, model);
        return items;
    }

    private void loadSampleList() {
        mDisposableManager.add(mGetSampleListUseCase.execute()
                .map(new Function<List<SampleDomain>, List<SampleModel>>() {
                    @Override
                    public List<SampleModel> apply(List<SampleDomain> sampleDomains) throws Exception {
                        mSampleDomainList = sampleDomains;
                        return mSampleModelDataMapper.transform(sampleDomains);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new BaseDisposableMaybeObserver<List<SampleModel>>(mContext) {
                    @Override
                    protected void onStart() {
                        showProgress();
                    }

                    @Override
                    public void onSuccess(List<SampleModel> sampleModelList) {
                        hideProgress();
                        drawSampleList(sampleModelList);
                    }

                    @Override
                    protected void onError(int code, String title, String description) {
                        hideProgress();
                        showError(code, title, description);
                    }

                    @Override
                    public void onComplete() {
                        hideProgress();
                        showEmptyView();
                    }
                }));
    }

    private void removeItem(final int position) {
        final SampleDomain sampleDomain = mSampleDomainList.get(position);
        mDisposableManager.add(mRemoveSampleUseCase.execute(sampleDomain.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new BaseDisposableCompletableObserver(mContext) {
                    @Override
                    protected void onStart() {
                        showProgress();
                    }

                    @Override
                    protected void onError(int code, String title, String description) {
                        hideProgress();
                        showError(code, title, description);
                    }

                    @Override
                    public void onComplete() {
                        hideProgress();
                        mSampleDomainList.remove(sampleDomain);
                        drawRemoveAnimation(position);
                    }
                }));
    }

    private void addItem() {
        final SampleDomain sampleDomain = new SampleDomain();
        if (mSampleDomainList == null) {
            mSampleDomainList = new ArrayList<>();
        }
        sampleDomain.setId(UUID.randomUUID().toString());
        sampleDomain.setTitle("item " + (mSampleDomainList.size() + 1));
        sampleDomain.setLink(Uri.parse("https://www.google.com"));
        sampleDomain.setPubdate(LocalDateTime.now());
        sampleDomain.setImageResId(com.omvp.data.R.mipmap.ic_launcher_round);

        mDisposableManager.add(mSaveSampleUseCase.execute(sampleDomain)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new BaseDisposableCompletableObserver(mContext) {
                    @Override
                    protected void onStart() {
                        showProgress();
                    }

                    @Override
                    protected void onError(int code, String title, String description) {
                        hideProgress();
                        showError(code, title, description);
                    }

                    @Override
                    public void onComplete() {
                        mSampleDomainList.add(sampleDomain);
                        drawAddAnimation(mSampleModelDataMapper.transform(sampleDomain));
                    }
                }));
    }

    private void drawAddAnimation(SampleModel model) {
        if (mView != null) {
            mView.drawAddAnimation(model);
        }
    }

    private void drawRemoveAnimation(int position) {
        if (mView != null) {
            mView.drawRemoveAnimation(position);
        }
    }

    private void drawSampleList(List<SampleModel> sampleModelList) {
        if (mView != null) {
            mView.drawSampleList(sampleModelList);
        }
    }

    private void drawUpdatedItems(List<SampleModel> updatedList) {
        if (mView != null) {
            mView.drawUpdatedItems(updatedList);
        }
    }

    private void showEmptyView() {
        if (mView != null) {
            mView.showEmptyView();
        }
    }

    private void sampleItemSelected(SampleDomain sampleDomain, View sharedView) {
        if (mView != null) {
            mView.onSampleItemSelected(sampleDomain, sharedView);
        }
    }

    private void drawViewMoved(int oldPosition, int newPosition) {
        if (mView != null) {
            mView.drawViewMoved(oldPosition, newPosition);
        }
    }

    private void drawViewSwiped(int position) {
        if (mView != null) {
            mView.drawViewSwiped(position);
        }
    }
}
