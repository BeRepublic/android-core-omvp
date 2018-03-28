package com.omvp.app.ui.samples.sample_inputs.view;

import com.omvp.app.base.mvp.view.BaseView;

/**
 * Created by Angel on 21/02/2018.
 */
public interface SampleInputView extends BaseView {

    void showNameInputError(String error);

    void showNameInputSuccess();

    void hideNameInputError();

    void showPasswordInputError(String error);

    void showPasswordInputSuccess();

    void hidePasswordInputError();

    void showValidationToast();
}
