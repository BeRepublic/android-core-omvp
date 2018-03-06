package com.omvp.app.ui.home.view;

import android.os.Bundle;
import android.view.View;

import com.omvp.app.R;
import com.omvp.app.base.mvp.view.BaseViewFragment;
import com.omvp.app.base.mvp.view.BaseViewFragmentCallback;
import com.omvp.app.ui.home.presenter.HomePresenter;

import butterknife.OnClick;

public class HomeFragment extends BaseViewFragment<HomePresenter, HomeFragment.FragmentCallback> implements HomeView {

    public interface FragmentCallback extends BaseViewFragmentCallback {
        void onSampleViewSelected();
        void onSampleListSelected();
        void onSamplePagerSelected();
        void onSampleMapSelected();
    }

    public static HomeFragment newInstance(Bundle bundle) {
        HomeFragment fragment = new HomeFragment();
        bundle = bundle == null ? new Bundle() : bundle;
        fragment.setArguments(bundle);
        return fragment;
    }

    @OnClick(R.id.button_view)
    public void onSampleViewClicked(View view) {
        mCallback.onSampleViewSelected();
    }

    @OnClick(R.id.button_list)
    public void onSampleListClicked(View view) {
        mCallback.onSampleListSelected();
    }

    @OnClick(R.id.button_pager)
    public void onSamplePagerClicked(View view) {
        mCallback.onSamplePagerSelected();
    }

    @OnClick(R.id.button_map)
    public void onSampleMapClicked(View view){
        mCallback.onSampleMapSelected();
    }
}
