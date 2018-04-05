package com.omvp.app.ui.samples.detail.view;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

import com.omvp.app.R;
import com.omvp.app.base.mvp.view.BaseViewFragment;
import com.omvp.app.base.mvp.view.BaseViewFragmentCallback;
import com.omvp.app.ui.samples.detail.presenter.SampleDetailPresenterImpl;

import butterknife.BindView;

public class SampleDetailFragment extends BaseViewFragment<SampleDetailPresenterImpl, SampleDetailFragment.FragmentCallback>
        implements SampleDetailView {

    @BindView(R.id.text)
    AppCompatTextView mTextView;
    @BindView(R.id.title)
    AppCompatTextView mTitleView;

    public interface FragmentCallback extends BaseViewFragmentCallback {
        void drawImage(int imageRes);
    }

    public static SampleDetailFragment newInstance(Bundle bundle) {
        SampleDetailFragment fragment = new SampleDetailFragment();
        bundle = bundle == null ? new Bundle() : bundle;
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        setupViews();
    }

    @Override
    public void drawImage(int imageRes) {
        mCallback.drawImage(imageRes);
    }

    @Override
    public void drawText(String text) {
        mTextView.setText(text);
    }

    @Override
    public void drawTitle(String title) {
        mTitleView.setText(title);
    }

    private void setupViews() {

    }
}
