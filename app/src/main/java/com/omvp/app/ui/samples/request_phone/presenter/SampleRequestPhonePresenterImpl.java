
package com.omvp.app.ui.samples.request_phone.presenter;


import android.text.TextUtils;

import com.omvp.app.R;
import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.interceptor.authPhone.AuthPhoneInterceptor;
import com.omvp.app.ui.samples.request_phone.view.SampleRequestPhoneView;

import javax.inject.Inject;

public class SampleRequestPhonePresenterImpl extends BasePresenter<SampleRequestPhoneView> implements SampleRequestPhonePresenter {

    @Inject
    AuthPhoneInterceptor mAuthPhoneInterceptor;

    @Inject
    public SampleRequestPhonePresenterImpl(SampleRequestPhoneView sampleRequestPhoneFlowView) {
        super(sampleRequestPhoneFlowView);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();

    }

    @Override
    public void validatePhone(String prefix, String phone) {
        boolean formValid = true;
        if (TextUtils.isEmpty(prefix)) {
            showPrefixInputError(mContext.getString(R.string.invalid_prefix));
            formValid = false;
        } else {
            showPrefixInputSuccess();
        }
        if (TextUtils.isEmpty(phone)) {
            showPhoneInputError(mContext.getString(R.string.enter_your_phone));
            formValid = false;
        } else {
            showPhoneInputSuccess();
        }
        if (formValid) {
            startPhoneNumberVerification("+" + prefix + phone);
        }
    }

    private void startPhoneNumberVerification(String number) {
        showProgress();
        mAuthPhoneInterceptor.startPhoneNumberVerification(number);
    }

    private void showPrefixInputError(String error) {
        if (mView != null) {
            mView.showPrefixInputError(error);
        }
    }

    private void showPhoneInputError(String error) {
        if (mView != null) {
            mView.showPhoneInputError(error);
        }
    }

    private void showPrefixInputSuccess() {
        if (mView != null) {
            mView.showPrefixInputSuccess();
        }
    }

    private void showPhoneInputSuccess() {
        if (mView != null) {
            mView.showPhoneInputSuccess();
        }
    }
}
