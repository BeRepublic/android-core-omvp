
package com.omvp.app.ui.samples.vibration.presenter;


import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.ui.samples.vibration.view.VibrationView;

import javax.inject.Inject;

public class VibrationPresenterImpl extends BasePresenter<VibrationView> implements VibrationPresenter {

    @Inject
    public VibrationPresenterImpl(VibrationView vibrationView) {
        super(vibrationView);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();


    }
}
