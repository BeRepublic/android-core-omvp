package com.omvp.app.ui.detail.view;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

import com.omvp.app.R;
import com.omvp.app.base.mvp.view.BaseViewFragment;
import com.omvp.app.base.mvp.view.BaseViewFragmentCallback;
import com.omvp.app.ui.detail.presenter.DetailPresenterImpl;

import butterknife.BindView;

public class DetailFragment extends BaseViewFragment<DetailPresenterImpl, DetailFragment.FragmentCallback>
        implements DetailView {

    @BindView(R.id.text)
    AppCompatTextView mTextView;
    @BindView(R.id.title)
    AppCompatTextView mTitleView;

    public interface FragmentCallback extends BaseViewFragmentCallback {
        void drawImage(int imageRes);
    }

    public static DetailFragment newInstance(Bundle bundle) {
        DetailFragment fragment = new DetailFragment();
        bundle = bundle == null ? new Bundle() : bundle;
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onHandleArguments(Bundle savedInstanceState, Bundle arguments) {
        if (arguments.containsKey(Long.class.getSimpleName())) {
            long sampleId = arguments.getLong(Long.class.getSimpleName());
            mPresenter.setSampleId(sampleId);
        }
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
