package com.omvp.app.injector.module;

import com.omvp.app.dispatcher.BrowserDispatcherActivity;
import com.omvp.app.dispatcher.BrowserDispatcherActivityModule;
import com.omvp.app.injector.scope.PerActivity;
import com.omvp.app.injector.scope.PerBroadcastReceiver;
import com.omvp.app.injector.scope.PerService;
import com.omvp.app.receiver.AppUrbanAirshipReceiverService;
import com.omvp.app.receiver.AppUrbanAirshipReceiverServiceModule;
import com.omvp.app.service.AppFirebaseInstanceIDService;
import com.omvp.app.service.AppFirebaseInstanceIDServiceModule;
import com.omvp.app.service.AppFirebaseMessagingService;
import com.omvp.app.service.AppFirebaseMessagingServiceModule;
import com.omvp.app.ui.home.HomeActivity;
import com.omvp.app.ui.home.HomeActivityModule;
import com.omvp.app.ui.samples.detail.SampleDetailActivity;
import com.omvp.app.ui.samples.detail.SampleDetailActivityModule;
import com.omvp.app.ui.samples.inputs.SampleInputActivity;
import com.omvp.app.ui.samples.inputs.SampleInputActivityModule;
import com.omvp.app.ui.samples.list.SampleListActivity;
import com.omvp.app.ui.samples.list.SampleListActivityModule;
import com.omvp.app.ui.samples.list_horizontal.SampleListHorizontalActivity;
import com.omvp.app.ui.samples.list_horizontal.SampleListHorizontalActivityModule;
import com.omvp.app.ui.samples.locale.SampleLocaleActivity;
import com.omvp.app.ui.samples.locale.SampleLocaleActivityModule;
import com.omvp.app.ui.samples.location.SampleLocationActivity;
import com.omvp.app.ui.samples.location.SampleLocationActivityModule;
import com.omvp.app.ui.samples.multiple.SampleMultipleActivity;
import com.omvp.app.ui.samples.multiple.SampleMultipleActivityModule;
import com.omvp.app.ui.samples.pager.SamplePagerActivity;
import com.omvp.app.ui.samples.pager.SamplePagerActivityModule;
import com.omvp.app.ui.samples.sample_notice_dialog.SampleNoticeActivity;
import com.omvp.app.ui.samples.sample_notice_dialog.SampleNoticeActivityModule;
import com.omvp.app.ui.samples.simple.SampleActivity;
import com.omvp.app.ui.samples.simple.SampleActivityModule;
import com.omvp.app.ui.samples.social.SampleSocialActivity;
import com.omvp.app.ui.samples.social.SampleSocialActivityModule;
import com.omvp.app.ui.samples.take_picture.SampleTakePictureActivity;
import com.omvp.app.ui.samples.take_picture.SampleTakePictureActivityModule;
import com.omvp.app.ui.samples.vibration.VibrationActivity;
import com.omvp.app.ui.samples.vibration.VibrationActivityModule;
import com.omvp.app.ui.splash.SplashActivity;
import com.omvp.app.ui.splash.SplashActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Provides application-wide dependencies.
 */
@Module
public abstract class InjectorModule {

    // ============= Activities ====================================================================

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
     * Provides the injector for the {@link BrowserDispatcherActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = {BrowserDispatcherActivityModule.class})
    abstract BrowserDispatcherActivity browserDispatcherActivity();

    // ============= Services ======================================================================

    /**
     * Provides the injector for the {@link AppFirebaseInstanceIDService}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerService
    @ContributesAndroidInjector(modules = {AppFirebaseInstanceIDServiceModule.class})
    abstract AppFirebaseInstanceIDService appInstanceIDService();

    /**
     * Provides the injector for the {@link AppFirebaseInstanceIDService}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerService
    @ContributesAndroidInjector(modules = {AppFirebaseMessagingServiceModule.class})
    abstract AppFirebaseMessagingService appFirebaseMessagingService();

    // ============= BroadcastReceivers ============================================================

    /**
     * Provides the injector for the {@link AppFirebaseInstanceIDService}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerBroadcastReceiver
    @ContributesAndroidInjector(modules = {AppUrbanAirshipReceiverServiceModule.class})
    abstract AppUrbanAirshipReceiverService appUrbanAirshipReceiverService();

    // ============= SAMPLES =======================================================================

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
     * Provides the injector for the {@link SampleDetailActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = {SampleDetailActivityModule.class})
    abstract SampleDetailActivity detailActivity();

    /**
     * Provides the injector for the {@link VibrationActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = {VibrationActivityModule.class})
    abstract VibrationActivity vibrationActivity();

    /**
     * Provides the injector for the {@link SampleInputActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = {SampleInputActivityModule.class})
    abstract SampleInputActivity sampleInputActivity();

    /**
     * Provides the injector for the {@link SampleSocialActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = {SampleSocialActivityModule.class})
    abstract SampleSocialActivity sampleSocialActivity();

    /**
     * Provides the injector for the {@link SampleNoticeActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = {SampleNoticeActivityModule.class})
    abstract SampleNoticeActivity sampleNoticeActivity();
}
