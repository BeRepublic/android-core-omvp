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
    @BindView(R.id.input_fixed)
    InputLayoutView mFixedInputLayout;
    @BindView(R.id.input_fixed_left)
    InputLayoutView mFixedLeftInputLayout;
    @BindView(R.id.input_fixed_center)
    InputLayoutView mFixedCenterInputLayout;

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
        mPresenter.validateInputs(
                mNameInputLayout.getText(),
                mPasswordInputLayout.getText(),
                mFixedInputLayout.getText(),
                mFixedLeftInputLayout.getText(),
                mFixedCenterInputLayout.getText()
        );
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
    public void showFixedInputError(String error) {
        mFixedInputLayout.setError(error);
    }

    @Override
    public void showFixedInputSuccess() {
        mFixedInputLayout.setSuccess();
    }

    @Override
    public void hideFixedInputError() {
        mFixedInputLayout.clearError();
    }

    @Override
    public void showFixedLeftInputError(String error) {
        mFixedLeftInputLayout.setError(error);
    }

    @Override
    public void showFixedLeftInputSuccess() {
        mFixedLeftInputLayout.setSuccess();
    }

    @Override
    public void hideFixedLeftInputError() {
        mFixedLeftInputLayout.clearError();
    }

    @Override
    public void showFixedCenterInputError(String error) {
        mFixedCenterInputLayout.setError(error);
    }

    @Override
    public void showFixedCenterInputSuccess() {
        mFixedCenterInputLayout.setSuccess();
    }

    @Override
    public void hideFixedCenterInputError() {
        mFixedCenterInputLayout.clearError();
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
        mFixedInputLayout.addTextChangedListener(onFixedTextChanged);
        mFixedLeftInputLayout.addTextChangedListener(onFixedLeftTextChanged);
        mFixedCenterInputLayout.addTextChangedListener(onFixedCenterTextChanged);
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

    private TextWatcher onFixedTextChanged = new TextWatcher() {
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
            mPresenter.fixedChanged(s.toString());
        }
    };

    private TextWatcher onFixedLeftTextChanged = new TextWatcher() {
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
            mPresenter.fixedLeftChanged(s.toString());
        }
    };

    private TextWatcher onFixedCenterTextChanged = new TextWatcher() {
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
            mPresenter.fixedCenterChanged(s.toString());
        }
    };
}
