
package com.omvp.app.ui.samples.simple.presenter;


import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.ui.samples.simple.view.SampleView;

import javax.inject.Inject;

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
