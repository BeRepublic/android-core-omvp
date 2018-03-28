package com.omvp.app.ui.samples.sample_inputs.presenter;

import com.omvp.app.base.mvp.presenter.Presenter;

/**
 * Created by Angel on 21/02/2018.
 */

public interface SampleInputsPresenter extends Presenter {
    void validateInputs(String name, String password);

    void nameChanged(final String name);

    void passwordChanged(String s);
}
