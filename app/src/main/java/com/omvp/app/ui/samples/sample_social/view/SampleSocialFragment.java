package com.omvp.app.ui.samples.sample_social.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

import com.omvp.app.R;
import com.omvp.app.base.mvp.view.BaseViewFragment;
import com.omvp.app.base.mvp.view.BaseViewFragmentCallback;
import com.omvp.app.ui.samples.sample_social.presenter.SampleSocialPresenterImpl;
import com.omvp.app.util.SocialAuthManager;

import javax.inject.Inject;

import butterknife.BindView;

public class SampleSocialFragment extends BaseViewFragment<SampleSocialPresenterImpl, SampleSocialFragment.FragmentCallback>
        implements SampleSocialView {

//    @Inject
    SocialAuthManager mSocialAuthManager;

    public interface FragmentCallback extends BaseViewFragmentCallback {

    }

    public static SampleSocialFragment newInstance(Bundle bundle) {
        SampleSocialFragment fragment = new SampleSocialFragment();
        bundle = bundle == null ? new Bundle() : bundle;
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSocialAuthManager.init(mPresenter);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        setupViews();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mSocialAuthManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        mSocialAuthManager.destroy();
        super.onDestroy();
    }

    private void setupViews() {

    }
}
