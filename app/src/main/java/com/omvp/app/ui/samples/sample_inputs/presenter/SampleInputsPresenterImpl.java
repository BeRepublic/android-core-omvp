
package com.omvp.app.ui.samples.sample_inputs.presenter;


import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.ui.samples.sample_inputs.view.SampleInputView;
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
    public void validateInputs(String name, String password) {
        if (validateForm(name, password)){
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

    private boolean validateForm(String name, String password){
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


    private void showValidationToast(){
        if (mView != null){
            mView.showValidationToast();
        }
    }
}
