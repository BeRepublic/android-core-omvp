package com.omvp.app.base;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;

import com.omvp.app.injector.component.DaggerApplicationComponent;
import com.raxdenstudios.commons.util.SDKUtils;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasBroadcastReceiverInjector;
import dagger.android.HasServiceInjector;

/**
 * The Android {@link MultiDexApplication}.
 */
public abstract class BaseApplication extends MultiDexApplication implements HasActivityInjector, HasServiceInjector, HasBroadcastReceiverInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;
    @Inject
    DispatchingAndroidInjector<Service> serviceInjector;
    @Inject
    DispatchingAndroidInjector<BroadcastReceiver> broadcastReceiverDispatchingAndroidInjector;

    // =============== LifeCycle ===================================================================

    @Override
    public void onCreate() {
        super.onCreate();

        initCompatVector();
        initDaggerApplicationComponent();
    }

    // =============== Injectors ===================================================================

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }

    @Override
    public AndroidInjector<Service> serviceInjector() {
        return serviceInjector;
    }

    @Override
    public AndroidInjector<BroadcastReceiver> broadcastReceiverInjector() {
        return broadcastReceiverDispatchingAndroidInjector;
    }

    // =============== Support methods =============================================================

    private void initCompatVector() {
        if (!SDKUtils.hasLollipop()) {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        }
    }

    protected void initDaggerApplicationComponent() {
        DaggerApplicationComponent.builder().create(this).inject(this);
    }

}
