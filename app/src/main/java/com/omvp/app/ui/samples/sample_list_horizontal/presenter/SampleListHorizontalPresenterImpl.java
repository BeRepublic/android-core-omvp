
package com.omvp.app.ui.samples.sample_list_horizontal.presenter;


import android.net.Uri;
import android.view.View;

import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.base.reactivex.BaseDisposableCompletableObserver;
import com.omvp.app.base.reactivex.BaseDisposableMaybeObserver;
import com.omvp.app.model.SampleModel;
import com.omvp.app.model.mapper.SampleModelDataMapper;
import com.omvp.app.ui.samples.sample_list_horizontal.adapter.SampleListAdapter;
import com.omvp.app.ui.samples.sample_list_horizontal.view.SampleListHorizontalView;
import com.omvp.data.StaticRepository;
import com.omvp.domain.SampleDomain;
import com.omvp.domain.interactor.GetSampleListUseCase;
import com.omvp.domain.interactor.RemoveSampleUseCase;
import com.omvp.domain.interactor.SaveSampleUseCase;

import org.threeten.bp.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SampleListHorizontalPresenterImpl extends BasePresenter<SampleListHorizontalView>
        implements SampleListHorizontalPresenter, SampleListAdapter.AdapterCallback {

    @Inject
    GetSampleListUseCase mGetSampleListUseCase;
    @Inject
    SaveSampleUseCase mSaveSampleUseCase;
    @Inject
    RemoveSampleUseCase mRemoveSampleUseCase;
    @Inject
    SampleModelDataMapper mSampleModelDataMapper;

    private List<Object> mSampleDomainList = new ArrayList<>();

    @Inject
    public SampleListHorizontalPresenterImpl(SampleListHorizontalView sampleListHorizontalView) {
        super(sampleListHorizontalView);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();

        loadSampleList();
    }

    @Override
    public void sampleItemSelected(int position, View sharedView) {
        sampleItemSelected((SampleDomain) mSampleDomainList.get(position), sharedView);
    }

    @Override
    public void sampleHorizontalItemSelected(int position, int horizontalListPosition, View sharedView) {
        List<SampleDomain> horizontalList = (List<SampleDomain>) mSampleDomainList.get(position);
        sampleItemSelected(horizontalList.get(horizontalListPosition), sharedView);
    }

    @Override
    public void sampleItemDeleteSelected(int position) {
        removeItem(position);
    }

    @Override
    public void onAddSampleItemSelected() {
        addItem();
    }

    private void loadSampleList() {
        mDisposableManager.add(mGetSampleListUseCase.execute()
                .map(new Function<List<SampleDomain>, List<SampleModel>>() {
                    @Override
                    public List<SampleModel> apply(List<SampleDomain> sampleDomains) {
                        mSampleDomainList.addAll(sampleDomains);
                        mSampleDomainList.add(3, sampleDomains);
                        mSampleDomainList.add(7, sampleDomains);
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
        final SampleDomain sampleDomain = (SampleDomain) mSampleDomainList.get(position);
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
        sampleDomain.setId((long) (mSampleDomainList.size() + 1));
        sampleDomain.setTitle("item " + mSampleDomainList.size() + 1);
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

            List<Object> objectList = new ArrayList<>();
            objectList.addAll(sampleModelList);
            objectList.add(3, sampleModelList);
            objectList.add(7, sampleModelList);

            mView.drawSampleList(objectList);
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
}
