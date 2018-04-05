package com.omvp.app.ui.samples.notice_dialog.view;

import android.os.Bundle;

import com.omvp.app.base.mvp.view.BaseViewFragment;
import com.omvp.app.base.mvp.view.BaseViewFragmentCallback;
import com.omvp.app.ui.samples.notice_dialog.presenter.SampleNoticePresenter;

public class SampleNoticeFragment extends BaseViewFragment<SampleNoticePresenter, SampleNoticeFragment.FragmentCallback>
        implements SampleNoticeView {

    public interface FragmentCallback extends BaseViewFragmentCallback {

    }

    public static SampleNoticeFragment newInstance(Bundle bundle) {
        SampleNoticeFragment fragment = new SampleNoticeFragment();
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

}
