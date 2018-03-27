package com.omvp.app.ui.detail;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.omvp.app.R;
import com.omvp.app.base.mvp.BaseFragmentActivity;
import com.omvp.app.ui.detail.view.DetailFragment;
import com.raxdenstudios.square.interceptor.Interceptor;
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptor;
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptorCallback;
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarInterceptor;
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarInterceptorCallback;

import java.util.List;

import javax.inject.Inject;

public class DetailActivity extends BaseFragmentActivity implements
        DetailFragment.FragmentCallback,
        ToolbarInterceptorCallback,
        InjectFragmentInterceptorCallback<DetailFragment> {


    @Inject
    ToolbarInterceptor mToolbarInterceptor;
    @Inject
    InjectFragmentInterceptor mInjectFragmentInterceptor;

    private Toolbar mToolbar;
    private AppBarLayout mAppBarLayout;
    private AppCompatImageView mImageView;
    private DetailFragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUpViews();
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

    // =============== InjectFragmentInterceptorCallback ===========================================

    @Override
    public View onLoadFragmentContainer(Bundle savedInstanceState) {
        return findViewById(R.id.content);
    }

    @Override
    public DetailFragment onCreateFragment() {
        return DetailFragment.newInstance(mExtras);
    }

    @Override
    public void onFragmentLoaded(DetailFragment fragment) {
        mFragment = fragment;
    }

    // =============== Support methods =============================================================

    @Override
    protected void setupInterceptors(List<Interceptor> interceptorList) {
        super.setupInterceptors(interceptorList);
        interceptorList.add(mToolbarInterceptor);
        interceptorList.add(mInjectFragmentInterceptor);
    }

    private void setUpViews() {
        mAppBarLayout = findViewById(R.id.app_bar_layout);
        mImageView = findViewById(R.id.image);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mImageView.setTransitionName("item");
            postponeEnterTransition();
        }
    }

    @Override
    public void drawImage(int imageRes) {
        mImageView.setImageResource(imageRes);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startPostponedEnterTransition();
        }
    }
}
