package com.omvp.app.injector.module;

import com.omvp.app.injector.scope.PerActivity;
import com.omvp.app.ui.detail.DetailActivity;
import com.omvp.app.ui.detail.DetailActivityModule;
import com.omvp.app.ui.home.HomeActivity;
import com.omvp.app.ui.home.HomeActivityModule;
import com.omvp.app.ui.samples.sample.SampleActivity;
import com.omvp.app.ui.samples.sample.SampleActivityModule;
import com.omvp.app.ui.samples.sample_list.SampleListActivity;
import com.omvp.app.ui.samples.sample_list.SampleListActivityModule;
import com.omvp.app.ui.samples.sample_locale.SampleLocaleActivity;
import com.omvp.app.ui.samples.sample_locale.SampleLocaleActivityModule;
import com.omvp.app.ui.samples.sample_list_horizontal.SampleListHorizontalActivity;
import com.omvp.app.ui.samples.sample_list_horizontal.SampleListHorizontalActivityModule;
import com.omvp.app.ui.samples.sample_location.SampleLocationActivity;
import com.omvp.app.ui.samples.sample_location.SampleLocationActivityModule;
import com.omvp.app.ui.samples.sample_multiple.SampleMultipleActivity;
import com.omvp.app.ui.samples.sample_multiple.SampleMultipleActivityModule;
import com.omvp.app.ui.samples.sample_pager.SamplePagerActivity;
import com.omvp.app.ui.samples.sample_pager.SamplePagerActivityModule;
import com.omvp.app.ui.samples.sample_take_picture.SampleTakePictureActivity;
import com.omvp.app.ui.samples.sample_take_picture.SampleTakePictureActivityModule;
import com.omvp.app.ui.splash.SplashActivity;
import com.omvp.app.ui.splash.SplashActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Provides application-wide dependencies.
 */
@Module
public abstract class InjectorModule {

    /**
     * Provides the injector for the {@link SplashActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = {SplashActivityModule.class})
    abstract SplashActivity splashActivity();

    /**
     * Provides the injector for the {@link HomeActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = {HomeActivityModule.class})
    abstract HomeActivity homeActivity();

    /**
     * Provides the injector for the {@link SampleActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = {SampleActivityModule.class})
    abstract SampleActivity sampleActivity();

    /**
     * Provides the injector for the {@link SampleListActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = {SampleListActivityModule.class})
    abstract SampleListActivity sampleListActivity();

    /**
     * Provides the injector for the {@link SampleListHorizontalActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = {SampleListHorizontalActivityModule.class})
    abstract SampleListHorizontalActivity sampleListHorizontalActivity();


    /**
     * Provides the injector for the {@link SamplePagerActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = {SamplePagerActivityModule.class})
    abstract SamplePagerActivity samplePagerActivity();

    /**
     * Provides the injector for the {@link SampleMultipleActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = {SampleMultipleActivityModule.class})
    abstract SampleMultipleActivity sampleMapActivity();

    /**
     * Provides the injector for the {@link SampleLocationActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = {SampleLocationActivityModule.class})
    abstract SampleLocationActivity sampleLocationActivity();

    /**
     * Provides the injector for the {@link SampleTakePictureActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = {SampleTakePictureActivityModule.class})
    abstract SampleTakePictureActivity sampleTakePictureActivity();

    /**
     * Provides the injector for the {@link SampleLocaleActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = {SampleLocaleActivityModule.class})
    abstract SampleLocaleActivity sampleLocaleActivity();

    /**
     * Provides the injector for the {@link DetailActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = {DetailActivityModule.class})
    abstract DetailActivity detailActivity();
}
