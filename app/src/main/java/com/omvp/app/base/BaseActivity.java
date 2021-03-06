package com.omvp.app.base;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.omvp.app.helper.AnimationHelper;
import com.omvp.app.helper.DialogHelper;
import com.omvp.app.helper.NavigationHelper;
import com.omvp.app.helper.SnackBarHelper;
import com.omvp.app.interceptor.operation.OperationBroadcastInterceptor;
import com.omvp.app.util.DisposableManager;
import com.raxdenstudios.square.SquareActivity;
import com.raxdenstudios.square.interceptor.Interceptor;
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptor;
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptorCallback;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;

/**
 * Abstract Activity for all Activities to extend.
 */
public abstract class BaseActivity extends SquareActivity implements
        AutoInflateLayoutInterceptorCallback,
        HasFragmentInjector {

    @Inject
    protected Resources mResources;
    @Inject
    protected Bundle mExtras;
    @Inject
    protected NavigationHelper mNavigationHelper;
    @Inject
    protected DialogHelper mDialogHelper;
    @Inject
    protected SnackBarHelper mSnackBarHelper;
    @Inject
    protected AnimationHelper mAnimationHelper;
    @Inject
    protected DisposableManager mDisposableManager;
    @Inject
    protected OperationBroadcastInterceptor mOperationBroadcastInterceptor;
    @Inject
    protected AutoInflateLayoutInterceptor mAutoInflateLayoutInterceptor;

    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentInjector;

    protected View mContentView;

    // =============== LifeCycle ===================================================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mDisposableManager.dispose();
    }


    // ========= AutoInflateLayoutInterceptorCallback ==============================================

    @Override
    public void onContentViewCreated(View view, Bundle savedInstanceState) {
        mContentView = view;
        initializeButterKnife();
    }

    // =============== HasFragmentInjector =========================================================

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return mFragmentInjector;
    }

    // =============== Support methods =============================================================

    @Override
    protected void setupInterceptors(List<Interceptor> interceptorList) {
        interceptorList.add(mAutoInflateLayoutInterceptor);
        interceptorList.add(mOperationBroadcastInterceptor);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void finishWithResultOK() {
        setResult(RESULT_OK);
        finish();
    }

    public void finishWithResultOK(Intent data) {
        setResult(RESULT_OK, data);
        finish();
    }

    private void initializeButterKnife() {
        ButterKnife.bind(this);
    }
}
