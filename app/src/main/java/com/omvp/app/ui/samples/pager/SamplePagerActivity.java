package com.omvp.app.ui.samples.pager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.omvp.app.R;
import com.omvp.app.base.mvp.BaseFragmentActivity;
import com.omvp.app.ui.samples.pager.view.SamplePagerFirstFragment;
import com.omvp.app.ui.samples.pager.view.SamplePagerFragment;
import com.omvp.app.ui.samples.pager.view.SamplePagerSecondFragment;
import com.omvp.components.PagerIndicator;
import com.raxdenstudios.square.interceptor.Interceptor;
import com.raxdenstudios.square.interceptor.commons.fragmentstatepager.FragmentStatePagerInterceptor;
import com.raxdenstudios.square.interceptor.commons.fragmentstatepager.FragmentStatePagerInterceptorCallback;
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarInterceptor;
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarInterceptorCallback;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class SamplePagerActivity extends BaseFragmentActivity implements
        SamplePagerFirstFragment.SamplePagerFirstFragmentCallback,
        SamplePagerSecondFragment.SamplePagerSecondFragmentCallback,
        ToolbarInterceptorCallback,
        FragmentStatePagerInterceptorCallback<SamplePagerFragment> {

    @Inject
    ToolbarInterceptor mToolbarInterceptor;
    @Inject
    FragmentStatePagerInterceptor mFragmentStatePagerInterceptor;

    @BindView(R.id.pager_indicator)
    PagerIndicator mPagerIndicator;

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private SamplePagerFirstFragment mFirstFragment;
    private SamplePagerSecondFragment mSecondFragment;

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
        mPagerIndicator.setViewPager(viewPager);
    }

    @Override
    public SamplePagerFragment onCreateFragment(int position) {
        switch (position) {
            case 0:
                return SamplePagerFirstFragment.newInstance(getIntent().getExtras());
            case 1:
                return SamplePagerSecondFragment.newInstance(getIntent().getExtras());
        }
        return null;
    }

    @Override
    public void onFragmentLoaded(SamplePagerFragment fragment, int position) {
        switch (position) {
            case 0:
                mFirstFragment = (SamplePagerFirstFragment) fragment;
                break;
            case 1:
                mSecondFragment = (SamplePagerSecondFragment) fragment;
                break;
        }
    }

    @Override
    public void onFragmentSelected(SamplePagerFragment fragment, int position) {

    }

    @Override
    public int getViewPagerElements() {
        return 2;
    }

    // =============== Support methods =============================================================

    @Override
    protected void setupInterceptors(List<Interceptor> interceptorList) {
        super.setupInterceptors(interceptorList);
        interceptorList.add(mToolbarInterceptor);
        interceptorList.add(mFragmentStatePagerInterceptor);
    }
}
