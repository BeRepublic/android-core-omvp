
package com.omvp.app.ui.samples.sample.presenter;


import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.base.reactivex.BaseDisposableSingleObserver;
import com.omvp.app.model.mapper.SampleModelDataMapper;
import com.omvp.app.ui.samples.sample.view.SampleView;
import com.omvp.domain.SampleDomain;
import com.omvp.domain.interactor.GetSampleListUseCase;
import com.omvp.domain.interactor.GetSampleUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class SamplePresenterImpl extends BasePresenter<SampleView> implements SamplePresenter {

    @Inject
    public SamplePresenterImpl(SampleView sampleView) {
        super(sampleView);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();


    }
}
