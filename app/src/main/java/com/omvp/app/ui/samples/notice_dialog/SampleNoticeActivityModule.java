package com.omvp.app.ui.samples.notice_dialog;


import android.app.Activity;

import com.omvp.app.base.BaseActivity;
import com.omvp.app.base.BaseActivityModule;
import com.omvp.app.base.mvp.BaseFragmentActivityModule;
import com.omvp.app.injector.scope.PerActivity;
import com.omvp.app.injector.scope.PerFragment;
import com.omvp.app.ui.samples.notice_dialog.view.SampleNoticeFragment;
import com.omvp.app.ui.samples.notice_dialog.view.SampleNoticeFragmentModule;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Provides notice dialog activity dependencies
 */
@Module(includes = {
        BaseFragmentActivityModule.class
})
public abstract class SampleNoticeActivityModule {

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
    abstract Activity activity(SampleNoticeActivity activity);

    /**
     * The main activity listens to the events in the {@link SampleNoticeFragment}.
     *
     * @param activity the activity
     * @return the main fragment callback
     */
    @Binds
    @PerActivity
    abstract SampleNoticeFragment.FragmentCallback fragmentCallback(SampleNoticeActivity activity);

    // =============================================================================================

    /**
     * Provides the injector for the {@link SampleNoticeFragment}, which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @PerFragment
    @ContributesAndroidInjector(modules = SampleNoticeFragmentModule.class)
    abstract SampleNoticeFragment fragmentInjector();

}
