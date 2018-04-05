package com.omvp.app.ui.samples.bottom_navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.omvp.app.R;
import com.omvp.app.base.mvp.BaseFragmentActivity;
import com.omvp.app.ui.samples.bottom_navigation.view.BottomNavigationFirstFragment;
import com.omvp.app.ui.samples.bottom_navigation.view.BottomNavigationFragment;
import com.omvp.app.ui.samples.bottom_navigation.view.BottomNavigationSecondFragment;
import com.omvp.app.ui.samples.bottom_navigation.view.BottomNavigationThirdFragment;
import com.omvp.components.BottomBarView;
import com.omvp.components.PagerIndicator;
import com.raxdenstudios.square.interceptor.Interceptor;
import com.raxdenstudios.square.interceptor.commons.fragmentstatepager.FragmentStatePagerInterceptor;
import com.raxdenstudios.square.interceptor.commons.fragmentstatepager.FragmentStatePagerInterceptorCallback;
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarInterceptor;
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarInterceptorCallback;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class BottomNavigationActivity extends BaseFragmentActivity implements
        BottomNavigationFirstFragment.BottomNavigationFirstFragmentCallback,
        BottomNavigationSecondFragment.BottomNavigationSecondFragmentCallback,
        BottomNavigationThirdFragment.BottomNavigationThirdFragmentCallback,
        ToolbarInterceptorCallback,
        FragmentStatePagerInterceptorCallback<BottomNavigationFragment> {


    @Inject
    ToolbarInterceptor mToolbarInterceptor;
    @Inject
    FragmentStatePagerInterceptor mFragmentStatePagerInterceptor;

    @Nullable
    @BindView(R.id.pager_indicator)
    PagerIndicator mPagerIndicator;
    @BindView(R.id.bottom_view)
    BottomBarView mBottomBarView;

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private BottomNavigationFirstFragment mFirstFragment;
    private BottomNavigationSecondFragment mSecondFragment;
    private BottomNavigationThirdFragment mThirdFragment;

    private static final int[] mIconResArray = new int[]{
            R.drawable.bottom_navigation_item_home,
            R.drawable.bottom_navigation_item_home,
            R.drawable.bottom_navigation_item_home
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // =============== ToolbarInterceptorCallback ==================================================

    @Override
    public Toolbar onCreateToolbarView(Bundle savedInstanceState) {
        return findViewById(R.id.toolbar);
    }

    @Override
    public void onToolbarViewCreated(Toolbar toolbar) {
        mToolbar = toolbar;
    }

    // =============== InjectFragmentStatePagerInterceptorCallback ===========================================

    @Override
    public ViewPager onCreateViewPager(Bundle savedInstanceState) {
        return findViewById(R.id.content);
    }

    @Override
    public void onViewPagerCreated(ViewPager viewPager) {
        mViewPager = viewPager;
        if (mPagerIndicator != null) {
            mPagerIndicator.setViewPager(viewPager);
        }

        mBottomBarView.setViewPager(mViewPager, mIconResArray);
    }

    @Override
    public BottomNavigationFragment onCreateFragment(int position) {
        switch (position) {
            case 0:
                return BottomNavigationFirstFragment.newInstance(getIntent().getExtras());
            case 1:
                return BottomNavigationSecondFragment.newInstance(getIntent().getExtras());
            case 2:
                return BottomNavigationThirdFragment.newInstance(getIntent().getExtras());
        }
        return null;
    }

    @Override
    public void onFragmentLoaded(BottomNavigationFragment fragment, int position) {
        switch (position) {
            case 0:
                mFirstFragment = (BottomNavigationFirstFragment) fragment;
                break;
            case 1:
                mSecondFragment = (BottomNavigationSecondFragment) fragment;
                break;
            case 2:
                mThirdFragment = (BottomNavigationThirdFragment) fragment;
                break;
        }
    }

    @Override
    public void onFragmentSelected(BottomNavigationFragment fragment, int position) {

    }

    @Override
    public int getViewPagerElements() {
        return 3;
    }

    // =============== Support methods =============================================================

    @Override
    protected void setupInterceptors(List<Interceptor> interceptorList) {
        super.setupInterceptors(interceptorList);
        interceptorList.add(mToolbarInterceptor);
        interceptorList.add(mFragmentStatePagerInterceptor);
    }
}
