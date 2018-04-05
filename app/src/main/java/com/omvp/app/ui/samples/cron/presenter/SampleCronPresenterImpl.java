
package com.omvp.app.ui.samples.cron.presenter;


import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.ui.samples.cron.view.SampleCronView;

import javax.inject.Inject;

public class SampleCronPresenterImpl extends BasePresenter<SampleCronView> implements SampleCronPresenter {

    @Inject
    public SampleCronPresenterImpl(SampleCronView sampleCronView) {
        super(sampleCronView);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();


    }
}
