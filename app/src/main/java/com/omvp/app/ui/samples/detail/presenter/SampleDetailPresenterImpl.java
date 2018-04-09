package com.omvp.app.ui.samples.detail.presenter;

import android.os.Bundle;

import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.base.reactivex.BaseDisposableSingleObserver;
import com.omvp.app.ui.samples.detail.view.SampleDetailView;
import com.omvp.domain.SampleDomain;
import com.omvp.domain.interactor.GetSampleUseCase;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SampleDetailPresenterImpl extends BasePresenter<SampleDetailView> implements SampleDetailPresenter {

    @Inject
    GetSampleUseCase mGetSampleUseCase;

    private String mSampleId;

    @Inject
    public SampleDetailPresenterImpl(SampleDetailView sampleDetailView) {
        super(sampleDetailView);
    }

    @Override
    public void onHandleArguments(Bundle savedInstanceState, Bundle arguments) {
        super.onHandleArguments(savedInstanceState, arguments);
        if (arguments.containsKey(String.class.getSimpleName())) {
            mSampleId = arguments.getString(String.class.getSimpleName());
        }
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();

        loadSample();
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
