
package com.omvp.app.ui.samples.inputs.presenter;


import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.ui.samples.inputs.view.SampleInputView;
import com.omvp.app.util.ValidationHelper;
import com.raxdenstudios.commons.util.Utils;

import javax.inject.Inject;

public class SampleInputsPresenterImpl extends BasePresenter<SampleInputView> implements SampleInputsPresenter {

    @Inject
    public SampleInputsPresenterImpl(SampleInputView sampleInputView) {
        super(sampleInputView);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();

    }

    @Override
    public void validateInputs(String name, String password, String fixed, String fixedLeft, String fixedCenter) {
        if (validateForm(name, password, fixed, fixedLeft, fixedCenter)) {
            showValidationToast();
        }
    }

    @Override
    public void nameChanged(String name) {
        hideNameInputError();
    }

    @Override
    public void passwordChanged(String s) {
        hidePasswordInputError();
    }

    @Override
    public void fixedChanged(String s) {
        hideFixedInputError();
    }

    @Override
    public void fixedLeftChanged(String s) {
        hideFixedLeftInputError();
    }

    @Override
    public void fixedCenterChanged(String s) {
        hideFixedCenterInputError();
    }

    private boolean validateForm(String name, String password, String fixed, String fixedLeft, String fixedCenter) {
        boolean formValid = true;
        if (!Utils.hasValue(name)) {
            showNameInputError("string.enter_your_name");
            formValid = false;
        } else {
            showNameInputSuccess();
        }
        if (!Utils.hasValue(password)) {
            showPasswordInputError("string.enter_password");
            formValid = false;
        } else {
            showPasswordInputSuccess();
        }
        if (!ValidationHelper.validatePassword(password)) {
            showPasswordInputError("string.password_length_min_6");
            formValid = false;
        } else {
            showPasswordInputSuccess();
        }
        if (!Utils.hasValue(fixed)) {
            showFixedInputError("string.enter_fixed");
            formValid = false;
        } else {
            showFixedInputSuccess();
        }
        if (!Utils.hasValue(fixedLeft)) {
            showFixedLeftInputError("string.enter_fixed_left");
            formValid = false;
        } else {
            showFixedLeftInputSuccess();
        }
        if (!Utils.hasValue(fixedCenter)) {
            showFixedCenterInputError("string.enter_fixed_center");
            formValid = false;
        } else {
            showFixedCenterInputSuccess();
        }
        return formValid;
    }

    private void showNameInputError(String error) {
        if (mView != null) {
            mView.showNameInputError(error);
        }
    }

    private void showNameInputSuccess() {
        if (mView != null) {
            mView.showNameInputSuccess();
        }
    }

    private void hideNameInputError() {
        if (mView != null) {
            mView.hideNameInputError();
        }
    }

    private void showPasswordInputError(String error) {
        if (mView != null) {
            mView.showPasswordInputError(error);
        }
    }

    private void showPasswordInputSuccess() {
        if (mView != null) {
            mView.showPasswordInputSuccess();
        }
    }

    private void hidePasswordInputError() {
        if (mView != null) {
            mView.hidePasswordInputError();
        }
    }

    private void showFixedInputError(String error) {
        if (mView != null) {
            mView.showFixedInputError(error);
        }
    }

    private void showFixedInputSuccess() {
        if (mView != null) {
            mView.showFixedInputSuccess();
        }
    }

    private void hideFixedInputError() {
        if (mView != null) {
            mView.hideFixedInputError();
        }
    }

    private void showFixedLeftInputError(String error) {
        if (mView != null) {
            mView.showFixedLeftInputError(error);
        }
    }

    private void showFixedLeftInputSuccess() {
        if (mView != null) {
            mView.showFixedLeftInputSuccess();
        }
    }

    private void hideFixedLeftInputError() {
        if (mView != null) {
            mView.hideFixedLeftInputError();
        }
    }

    private void showFixedCenterInputError(String error) {
        if (mView != null) {
            mView.showFixedCenterInputError(error);
        }
    }

    private void showFixedCenterInputSuccess() {
        if (mView != null) {
            mView.showFixedCenterInputSuccess();
        }
    }

    private void hideFixedCenterInputError() {
        if (mView != null) {
            mView.hideFixedCenterInputError();
        }
    }

    private void showValidationToast() {
        if (mView != null) {
            mView.showValidationToast();
        }
    }
}
