package com.omvp.app.helper;

import android.app.Activity;
import android.app.ActivityOptions;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.omvp.app.interceptor.operation.OperationBroadcastActivityInterceptor;
import com.omvp.app.ui.detail.DetailActivity;
import com.omvp.app.ui.home.HomeActivity;
import com.omvp.app.ui.samples.sample.SampleActivity;
import com.omvp.app.ui.samples.sample_inputs.SampleInputActivity;
import com.omvp.app.ui.samples.sample_list.SampleListActivity;
import com.omvp.app.ui.samples.sample_locale.SampleLocaleActivity;
import com.omvp.app.ui.samples.sample_list_horizontal.SampleListHorizontalActivity;
import com.omvp.app.ui.samples.sample_location.SampleLocationActivity;
import com.omvp.app.ui.samples.sample_multiple.SampleMultipleActivity;
import com.omvp.app.ui.samples.sample_pager.SamplePagerActivity;
import com.omvp.app.ui.samples.sample_take_picture.SampleTakePictureActivity;
import com.omvp.app.ui.samples.sample_vibration.VibrationActivity;
import com.omvp.app.ui.splash.SplashActivity;
import com.raxdenstudios.commons.manager.NavigationManager;

public class NavigationHelper {

    private Activity mActivity;

    public NavigationHelper(Activity activity) {
        mActivity = activity;
    }

    public void launchSplash() {
        OperationBroadcastActivityInterceptor.finishAllActivities(mActivity);
        new NavigationManager.Builder(mActivity)
                .putData(getExtras())
                .navigateTo(SplashActivity.class)
                .launch();
    }

    public void launchHomeAndFinishPreviousViews() {
        OperationBroadcastActivityInterceptor.finishAllActivities(mActivity);
        new NavigationManager.Builder(mActivity)
                .putData(getExtras())
                .navigateTo(HomeActivity.class)
                .launch();
    }

    public void launchSample() {
        new NavigationManager.Builder(mActivity)
                .putData(getExtras())
                .navigateTo(SampleActivity.class)
                .launch();
    }

    public void launchDetail(long sampleItemId) {
        Bundle extras = getExtras();
        extras.putLong(Long.class.getSimpleName(), sampleItemId);
        new NavigationManager.Builder(mActivity)
                .putData(extras)
                .navigateTo(DetailActivity.class)
                .launch();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void launchDetailWithSharedViewTransition(long sampleItemId, View sharedView) {
        Bundle extras = getExtras();
        extras.putLong(Long.class.getSimpleName(), sampleItemId);

        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(
                mActivity, sharedView, "item");

        new NavigationManager.Builder(mActivity)
                .putData(extras)
                .navigateTo(DetailActivity.class, activityOptions.toBundle())
                .launch();
    }

    public void launchSampleList() {
        new NavigationManager.Builder(mActivity)
                .putData(getExtras())
                .navigateTo(SampleListActivity.class)
                .launch();
    }

    public void launchSampleHorizontalList() {
        new NavigationManager.Builder(mActivity)
                .putData(getExtras())
                .navigateTo(SampleListHorizontalActivity.class)
                .launch();
    }

    public void launchSamplePager() {
        new NavigationManager.Builder(mActivity)
                .putData(getExtras())
                .navigateTo(SamplePagerActivity.class)
                .launch();
    }

    public void launchSampleMap() {
        new NavigationManager.Builder(mActivity)
                .putData(getExtras())
                .navigateTo(SampleMultipleActivity.class)
                .launch();
    }

    public void launchSampleLocation() {
        new NavigationManager.Builder(mActivity)
                .putData(getExtras())
                .navigateTo(SampleLocationActivity.class)
                .launch();
    }

    public void launchSampleTakePicture() {
        new NavigationManager.Builder(mActivity)
                .putData(getExtras())
                .navigateTo(SampleTakePictureActivity.class)
                .launch();
    }

    public void launchSampleLocale() {
        new NavigationManager.Builder(mActivity)
                .putData(getExtras())
                .navigateTo(SampleLocaleActivity.class)
                .launch();
    }

    public void launchVibrationSample() {
        new NavigationManager.Builder(mActivity)
                .putData(getExtras())
                .navigateTo(VibrationActivity.class)
                .launch();
    }

    public void launchInputViewSample() {
        new NavigationManager.Builder(mActivity)
                .putData(getExtras())
                .navigateTo(SampleInputActivity.class)
                .launch();
    }

    private Bundle getExtras() {
        return mActivity.getIntent() != null && mActivity.getIntent().getExtras() != null ? mActivity.getIntent().getExtras() : new Bundle();
    }


}
