package com.omvp.app.ui.samples.bottom_navigation;

import android.app.Activity;

import com.omvp.app.base.BaseActivity;
import com.omvp.app.base.BaseActivityModule;
import com.omvp.app.base.mvp.BaseFragmentActivityModule;
import com.omvp.app.injector.scope.PerActivity;
import com.omvp.app.injector.scope.PerFragment;
import com.omvp.app.ui.samples.bottom_navigation.view.BottomNavigationFirstFragment;
import com.omvp.app.ui.samples.bottom_navigation.view.BottomNavigationFirstFragmentModule;
import com.omvp.app.ui.samples.bottom_navigation.view.BottomNavigationSecondFragment;
import com.omvp.app.ui.samples.bottom_navigation.view.BottomNavigationSecondFragmentModule;
import com.omvp.app.ui.samples.bottom_navigation.view.BottomNavigationThirdFragment;
import com.omvp.app.ui.samples.bottom_navigation.view.BottomNavigationThirdFragmentModule;

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

    // =============================================================================================

    /**
     * The main activity listens to the events in the {@link BottomNavigationFirstFragment}.
     *
     * @param activity the activity
     * @return the main fragment callback
     */
    @Binds
    @PerActivity
    abstract BottomNavigationFirstFragment.BottomNavigationFirstFragmentCallback firstFragmentCallback(BottomNavigationActivity activity);

    /**
     * The main activity listens to the events in the {@link BottomNavigationSecondFragment}.
     *
     * @param activity the activity
     * @return the main fragment callback
     */
    @Binds
    @PerActivity
    abstract BottomNavigationSecondFragment.BottomNavigationSecondFragmentCallback secondFragmentCallback(BottomNavigationActivity activity);

    /**
     * The main activity listens to the events in the {@link BottomNavigationThirdFragment}.
     *
     * @param activity the activity
     * @return the main fragment callback
     */
    @Binds
    @PerActivity
    abstract BottomNavigationThirdFragment.BottomNavigationThirdFragmentCallback thirdFragmentCallback(BottomNavigationActivity activity);

    /**
     * Provides the injector for the {@link BottomNavigationFirstFragment}, which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @PerFragment
    @ContributesAndroidInjector(modules = BottomNavigationFirstFragmentModule.class)
    abstract BottomNavigationFirstFragment firstFragmentInjector();

    /**
     * Provides the injector for the {@link BottomNavigationSecondFragment}, which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @PerFragment
    @ContributesAndroidInjector(modules = BottomNavigationSecondFragmentModule.class)
    abstract BottomNavigationSecondFragment secondFragmentInjector();

    /**
     * Provides the injector for the {@link BottomNavigationThirdFragment}, which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @PerFragment
    @ContributesAndroidInjector(modules = BottomNavigationThirdFragmentModule.class)
    abstract BottomNavigationThirdFragment thirdFragmentInjector();

}
