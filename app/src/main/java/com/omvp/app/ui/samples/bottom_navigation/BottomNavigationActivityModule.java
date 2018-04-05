package com.omvp.app.ui.samples.bottom_navigation;

import android.app.Activity;

import com.omvp.app.base.BaseActivity;
import com.omvp.app.base.BaseActivityModule;
import com.omvp.app.base.mvp.BaseFragmentActivityModule;
import com.omvp.app.injector.scope.PerActivity;
import com.omvp.app.injector.scope.PerFragment;
import com.omvp.app.ui.samples.bottom_navigation.view.BottomNavigationFragment;
import com.omvp.app.ui.samples.bottom_navigation.view.BottomNavigationFragmentModule;
import com.omvp.app.ui.splash.view.SplashFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Provides splash activity dependencies
 */
@Module(includes = {
        BaseFragmentActivityModule.class
})
public abstract class BottomNavigationActivityModule {

    /**
     * As per the contract specified in {@link BaseActivityModule}; "This must be included in all
     * activity modules, which must provide a concrete implementation of {@link Activity}."
     * <p>
     * This provides the activity required to inject the dependencies into the
     * {@link BaseActivity}.
     *
     * @param activity the SplashActivity
     * @return the activity
     */
    @Binds
    @PerActivity
    abstract Activity activity(BottomNavigationActivity activity);

    /**
     * The main activity listens to the events in the {@link BottomNavigationFragment}.
     *
     * @param activity the activity
     * @return the main fragment callback
     */
    @Binds
    @PerActivity
    abstract BottomNavigationFragment.FragmentCallback fragmentCallback(BottomNavigationActivity activity);

    // =============================================================================================

    /**
     * Provides the injector for the {@link SplashFragment}, which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @PerFragment
    @ContributesAndroidInjector(modules = BottomNavigationFragmentModule.class)
    abstract BottomNavigationFragment fragmentInjector();

}
