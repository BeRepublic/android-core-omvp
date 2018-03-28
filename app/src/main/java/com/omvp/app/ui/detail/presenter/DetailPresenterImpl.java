
package com.omvp.app.ui.detail.presenter;


import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.base.reactivex.BaseDisposableSingleObserver;
import com.omvp.app.model.mapper.SampleModelDataMapper;
import com.omvp.app.ui.detail.view.DetailView;
import com.omvp.domain.SampleDomain;
import com.omvp.domain.interactor.GetSampleListUseCase;
import com.omvp.domain.interactor.GetSampleUseCase;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DetailPresenterImpl extends BasePresenter<DetailView> implements DetailPresenter {

    @Inject
    GetSampleUseCase mGetSampleUseCase;
    @Inject
    GetSampleListUseCase mGetSampleListUseCase;
    @Inject
    GetSampleListUseCase mGetSampleListUseCase2;
    @Inject
    SampleModelDataMapper mSampleModelDataMapper;

    private long mSampleId;

    @Inject
    public DetailPresenterImpl(DetailView detailView) {
        super(detailView);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();

        loadSample();
    }

    public void setSampleId(long id) {
        mSampleId = id;
    }

    private void loadSample() {
        mDisposableManager.add(mGetSampleUseCase.execute(mSampleId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new BaseDisposableSingleObserver<SampleDomain>(mContext) {
                    @Override
                    protected void onError(int code, String title, String description) {
                        hideProgress();
                        showError(code, title, description);
                    }

                    @Override
                    protected void onStart() {
                        showProgress();
                    }

                    @Override
                    public void onSuccess(SampleDomain sampleDomain) {
                        hideProgress();
                        drawSampleItem(sampleDomain);
                    }
                }));
    }

    private void drawSampleItem(SampleDomain sampleDomain) {
        if (mView != null) {
            mView.drawTitle(sampleDomain.getTitle());
            mView.drawImage(sampleDomain.getImageResId());
        }
    }
}
