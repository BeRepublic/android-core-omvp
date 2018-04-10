package com.omvp.app.ui.samples.request_phone.view;

import android.os.Bundle;
import android.view.View;

import com.omvp.app.R;
import com.omvp.app.base.mvp.view.BaseViewFragment;
import com.omvp.app.base.mvp.view.BaseViewFragmentCallback;
import com.omvp.app.ui.samples.request_phone.presenter.SampleRequestPhonePresenterImpl;
import com.omvp.components.InputLayoutView;

import butterknife.BindView;
import butterknife.OnClick;

public class SampleRequestPhoneFragment extends BaseViewFragment<SampleRequestPhonePresenterImpl, SampleRequestPhoneFragment.FragmentCallback>
        implements SampleRequestPhoneView {

    @BindView(R.id.input_prefix)
    InputLayoutView mInputPrefixView;
    @BindView(R.id.input_phone)
    InputLayoutView mInputPhoneView;

    public interface FragmentCallback extends BaseViewFragmentCallback {

    }

    public static SampleRequestPhoneFragment newInstance(Bundle bundle) {
        SampleRequestPhoneFragment fragment = new SampleRequestPhoneFragment();
        bundle = bundle == null ? new Bundle() : bundle;
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        setupViews();
    }

    private void setupViews() {

    }

    @OnClick(R.id.next)
    public void onValidatePhoneClick(View view) {
        mPresenter.validatePhone(mInputPrefixView.getText(), mInputPhoneView.getText());
    }

    @Override
    public void showPrefixInputError(String error) {
        mInputPrefixView.setError(error);
    }

    @Override
    public void showPhoneInputError(String error) {
        mInputPhoneView.setError(error);
    }

    @Override
    public void showPrefixInputSuccess() {
        mInputPrefixView.setSuccess();
    }

    @Override
    public void showPhoneInputSuccess() {
        mInputPhoneView.setSuccess();
    }



}
