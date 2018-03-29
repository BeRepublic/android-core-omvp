package com.omvp.app.ui.samples.sample_inputs.view;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.omvp.app.R;
import com.omvp.app.base.mvp.view.BaseViewFragment;
import com.omvp.app.base.mvp.view.BaseViewFragmentCallback;
import com.omvp.app.ui.samples.sample_inputs.presenter.SampleInputsPresenterImpl;
import com.omvp.components.InputLayoutView;

import butterknife.BindView;
import butterknife.OnClick;

public class SampleInputFragment extends BaseViewFragment<SampleInputsPresenterImpl, SampleInputFragment.FragmentCallback>
        implements SampleInputView {

    @BindView(R.id.input_name)
    InputLayoutView mNameInputLayout;
    @BindView(R.id.input_password)
    InputLayoutView mPasswordInputLayout;

    public interface FragmentCallback extends BaseViewFragmentCallback {

    }

    public static SampleInputFragment newInstance(Bundle bundle) {
        SampleInputFragment fragment = new SampleInputFragment();
        bundle = bundle == null ? new Bundle() : bundle;
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        setupViews();
    }

    @OnClick(R.id.validation_button)
    public void onValidationClicked(View view) {
        mPresenter.validateInputs(mNameInputLayout.getText(), mPasswordInputLayout.getText());
    }

    @Override
    public void showNameInputError(String error) {
        mNameInputLayout.setError(error);
    }

    @Override
    public void showNameInputSuccess() {
        mNameInputLayout.setSuccess();
    }

    @Override
    public void showValidationToast() {
        Toast.makeText(mContext, "Validation succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideNameInputError() {
        mNameInputLayout.clearError();
    }

    @Override
    public void showPasswordInputError(String error) {
        mPasswordInputLayout.setError(error);
    }

    @Override
    public void showPasswordInputSuccess() {
        mPasswordInputLayout.setSuccess();
    }

    @Override
    public void hidePasswordInputError() {
        mPasswordInputLayout.clearError();
    }

    private void setupViews() {
        mNameInputLayout.addTextChangedListener(onNameTextChanged);
        mPasswordInputLayout.addTextChangedListener(onPasswordTextChanged);
    }

    private TextWatcher onNameTextChanged = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // do nothing
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // do nothing
        }

        @Override
        public void afterTextChanged(Editable s) {
            mPresenter.nameChanged(s.toString());
        }
    };

    private TextWatcher onPasswordTextChanged = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // do nothing
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // do nothing
        }

        @Override
        public void afterTextChanged(Editable s) {
            mPresenter.passwordChanged(s.toString());
        }
    };
}
