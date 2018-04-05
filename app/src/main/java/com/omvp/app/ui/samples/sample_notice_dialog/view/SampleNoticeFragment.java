package com.omvp.app.ui.samples.sample_notice_dialog.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.omvp.app.R;
import com.omvp.app.base.mvp.view.BaseViewFragment;
import com.omvp.app.base.mvp.view.BaseViewFragmentCallback;
import com.omvp.app.ui.samples.sample_notice_dialog.presenter.SampleNoticePresenter;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_sample_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        setupViews();
    }


    private void setupViews() {
    }
}
