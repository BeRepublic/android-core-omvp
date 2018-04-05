package com.omvp.app.ui.home;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.omvp.app.R;
import com.omvp.app.base.mvp.BaseFragmentActivity;
import com.omvp.app.ui.home.view.HomeFragment;
import com.raxdenstudios.square.interceptor.Interceptor;
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptor;
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptorCallback;
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarInterceptor;
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarInterceptorCallback;

import java.util.List;

import javax.inject.Inject;


public class HomeActivity extends BaseFragmentActivity implements
        ToolbarInterceptorCallback,
        InjectFragmentInterceptorCallback<HomeFragment>,
        HomeFragment.FragmentCallback {

    @Inject
    ToolbarInterceptor mToolbarInterceptor;
    @Inject
    InjectFragmentInterceptor mInjectFragmentInterceptor;

    private Toolbar mToolbar;
    private HomeFragment mFragment;

    // =============== ToolbarInterceptorCallback ==================================================

    @Override
    public Toolbar onCreateToolbarView(Bundle savedInstanceState) {
        return findViewById(R.id.toolbar);
    }

    @Override
    public void onToolbarViewCreated(Toolbar toolbar) {
        mToolbar = toolbar;
    }

    // =============== InjectFragmentInterceptorCallback ===========================================

    @Override
    public View onLoadFragmentContainer(Bundle savedInstanceState) {
        return findViewById(R.id.content);
    }

    @Override
    public HomeFragment onCreateFragment() {
        return HomeFragment.newInstance(mExtras);
    }

    @Override
    public void onFragmentLoaded(HomeFragment fragment) {
        mFragment = fragment;
    }

    // =============== Support methods =============================================================

    @Override
    protected void setupInterceptors(List<Interceptor> interceptorList) {
        super.setupInterceptors(interceptorList);
        interceptorList.add(mToolbarInterceptor);
        interceptorList.add(mInjectFragmentInterceptor);
    }

    @Override
    public void onSampleViewSelected() {
        mNavigationHelper.launchSample();
    }

    @Override
    public void onSampleListSelected() {
        mNavigationHelper.launchSampleList();
    }

    @Override
    public void onSamplePagerSelected() {
        mNavigationHelper.launchSamplePager();
    }

    @Override
    public void onSampleMultipleSelected() {
        mNavigationHelper.launchSampleMap();
    }

    @Override
    public void onSampleLocationSelected() {
        mNavigationHelper.launchSampleLocation();
    }

    @Override
    public void onSampleTakePictureSelected() {
        mNavigationHelper.launchSampleTakePicture();
    }

    @Override
    public void onSampleLocaleSelected() {
        mNavigationHelper.launchSampleLocale();
    }

    @Override
    public void onSampleHorizontalListClicked() {
        mNavigationHelper.launchSampleHorizontalList();
    }

    @Override
    public void onVibrationSelected() {
        mNavigationHelper.launchVibrationSample();
    }

    @Override
    public void onInputViewSelected() {
        mNavigationHelper.launchInputViewSample();
    }

    @Override
    public void onSocialViewSelected() {
        mNavigationHelper.launchSocialViewSample();
    }

    @Override
    public void onNoticeDialogViewSelected() {
        mNavigationHelper.launchNoticeDialogViewSample();
    }

    @Override
    public void onBottomNavigationViewSelected() {
        mNavigationHelper.launchBottomBarSample();
    }
}
}
