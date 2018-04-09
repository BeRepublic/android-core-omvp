
package com.omvp.app.ui.samples.list_horizontal.presenter;


import android.net.Uri;
import android.view.View;

import com.omvp.app.R;
import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.base.reactivex.BaseDisposableCompletableObserver;
import com.omvp.app.base.reactivex.BaseDisposableMaybeObserver;
import com.omvp.app.model.SampleModel;
import com.omvp.app.model.mapper.SampleModelDataMapper;
import com.omvp.app.ui.samples.list_horizontal.adapter.SampleListAdapter;
import com.omvp.app.ui.samples.list_horizontal.view.SampleListHorizontalView;
import com.omvp.domain.SampleDomain;
import com.omvp.domain.interactor.GetSampleListUseCase;
import com.omvp.domain.interactor.RemoveSampleUseCase;
import com.omvp.domain.interactor.SaveSampleUseCase;

import org.threeten.bp.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function3;
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
        mDisposableManager.add(Maybe.zip(
                retrieveMainList(),
                retrieveFirstHorizontalList(),
                retrieveSecondHorizontalList(),
                new Function3<List<SampleDomain>, List<SampleDomain>, List<SampleDomain>, List<Object>>() {
                    @Override
                    public List<Object> apply(List<SampleDomain> mainList, List<SampleDomain> firstHorizontal, List<SampleDomain> secondHorizontal) throws Exception {
                        mSampleDomainList.addAll(mainList);
                        mSampleDomainList.add(3, firstHorizontal);
                        mSampleDomainList.add(7, secondHorizontal);

                        List<Object> finalObjects = new ArrayList<>();
                        finalObjects.addAll(mSampleModelDataMapper.transform(mainList));
                        finalObjects.add(3, mSampleModelDataMapper.transform(firstHorizontal));
                        finalObjects.add(7, mSampleModelDataMapper.transform(secondHorizontal));
                        return finalObjects;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new BaseDisposableMaybeObserver<List<Object>>(mContext) {
                    @Override
                    protected void onStart() {
                        showProgress();
                    }

                    @Override
                    public void onSuccess(List<Object> sampleModelList) {
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

    private Maybe<List<SampleDomain>> retrieveMainList() {
        return mGetSampleListUseCase.execute().subscribeOn(Schedulers.io());
    }

    private Maybe<List<SampleDomain>> retrieveFirstHorizontalList() {
        return mGetSampleListUseCase.execute().subscribeOn(Schedulers.io());
    }

    private Maybe<List<SampleDomain>> retrieveSecondHorizontalList() {
        return mGetSampleListUseCase.execute().subscribeOn(Schedulers.io());
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
        sampleDomain.setId(UUID.randomUUID().toString());
        sampleDomain.setTitle("item " + mSampleDomainList.size() + 1);
        sampleDomain.setLink(Uri.parse("https://www.google.com"));
        sampleDomain.setPubdate(LocalDateTime.now());
        sampleDomain.setImageResId(R.mipmap.ic_launcher_round);

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

    private void drawSampleList(List<Object> sampleModelList) {
        if (mView != null) {
            mView.drawSampleList(sampleModelList);
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
