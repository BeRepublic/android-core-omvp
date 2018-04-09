package com.omvp.app.base.mvp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.omvp.app.R;
import com.omvp.app.base.BaseActivity;
import com.omvp.app.base.mvp.view.BaseViewFragmentCallback;
import com.omvp.app.dialog.notice.view.NoticeDialogFragment;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Abstract Activity for all Activities to extend.
 */
public abstract class BaseFragmentActivity extends BaseActivity implements
        NoticeDialogFragment.FragmentCallback,
        BaseViewFragmentCallback {

    /**
     * A reference to the FragmentManager is injected and used instead of the getter method. This
     * enables ease of mocking and verification in tests (in case Activity needs testing).
     */
    @Inject
    protected FragmentManager mFragmentManager;

    @BindView(R.id.progress) @Nullable
    View mProgressContainer;
    @BindView(R.id.progress_label) @Nullable
    AppCompatTextView mProgressTextView;

    // =============== BaseViewFragmentCallback ====================================================

    @Override
    public void showProgress(float progress, String message) {
        mAnimationHelper.fadeIn(mProgressContainer);
        if (mProgressTextView != null) {
            mAnimationHelper.fadeIn(mProgressTextView);
            mProgressTextView.setText(message);
        }
    }

    @Override
    public void hideProgress() {
        mAnimationHelper.fadeOut(mProgressContainer);
        if (mProgressTextView != null) {
            mAnimationHelper.fadeOut(mProgressTextView);
            mProgressTextView.setText("");
        }
    }

    @Override
    public void showError(int code, String title, String message) {
        mDialogHelper.showError(title, message);
    }

    @Override
    public void showMessage(int code, String title, String message) {
        mDialogHelper.showMessage(title, message);
    }

    // =============== Support methods =============================================================

    protected final void addFragment(@IdRes int containerViewId, Fragment fragment) {
        mFragmentManager.beginTransaction()
                .add(containerViewId, fragment)
                .commit();
    }

}
