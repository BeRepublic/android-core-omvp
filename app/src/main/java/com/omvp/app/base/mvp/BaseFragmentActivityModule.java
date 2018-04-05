package com.omvp.app.base.mvp;

import android.app.Activity;
import android.app.FragmentManager;

import com.omvp.app.base.BaseActivityModule;
import com.omvp.app.dialog.notice.view.NoticeDialogFragment;
import com.omvp.app.dialog.notice.view.NoticeDialogFragmentModule;
import com.omvp.app.injector.scope.PerActivity;
import com.omvp.app.injector.scope.PerFragment;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Provides base activity dependencies. This must be included in all activity modules, which must
 * provide a concrete implementation of {@link Activity}.
 */
@Module(includes = {
        BaseActivityModule.class
})
public abstract class BaseFragmentActivityModule {

    @Provides
    @PerActivity
    static BaseFragmentActivity baseFragmentActivity(Activity activity) {
        return (BaseFragmentActivity) activity;
    }

    @Provides
    @PerActivity
    static FragmentManager activityFragmentManager(Activity activity) {
        return activity.getFragmentManager();
    }

    /**
     * The main activity listens to the events in the {@link NoticeDialogFragment}.
     *
     * @param activity the activity
     * @return the main fragment callback
     */
    @Binds
    @PerActivity
    abstract NoticeDialogFragment.FragmentCallback fragmentCallback(BaseFragmentActivity activity);

    /**
     * Provides the injector for the {@link NoticeDialogFragment}, which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @PerFragment
    @ContributesAndroidInjector(modules = NoticeDialogFragmentModule.class)
    abstract NoticeDialogFragment fragmentInjector();

}
