package com.omvp.app.ui.samples.inputs.presenter;

import com.omvp.app.base.mvp.presenter.Presenter;

/**
 * Created by Angel on 21/02/2018.
 */

public interface SampleInputsPresenter extends Presenter {
    void validateInputs(String name, String password, String fixed, String fixedLeft, String fixedCenter);

    void nameChanged(final String name);

    void passwordChanged(String s);

    void fixedChanged(String s);

    void fixedLeftChanged(String s);

    void fixedCenterChanged(String s);
}
