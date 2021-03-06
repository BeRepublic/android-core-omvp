package com.omvp.app.dialog.notice.view;

import android.app.Fragment;

import com.omvp.app.base.BaseFragmentModule;
import com.omvp.app.dialog.notice.presenter.NoticeDialogPresenterModule;
import com.omvp.app.injector.scope.PerFragment;

import dagger.Binds;
import dagger.Module;

/**
 * Provides SampleMapFragment fragment dependencies.
 */
@Module(includes = {
        BaseFragmentModule.class,
        NoticeDialogPresenterModule.class
})
public abstract class NoticeDialogFragmentModule {

    /**
     * As per the contract specified in {@link BaseFragmentModule}; "This must be included in all
     * fragment modules, which must provide a concrete implementation of {@link Fragment}.
     *
     * @param fragment the HomeFragment
     * @return the fragment
     */
    @Binds
    @PerFragment
    abstract Fragment dialogFragment(NoticeDialogFragment fragment);

    @Binds
    @PerFragment
    abstract NoticeDialogView dialogView(NoticeDialogFragment fragment);

}
