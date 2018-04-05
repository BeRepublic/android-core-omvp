package com.omvp.app.ui.samples.notice_dialog.view;

import android.app.Fragment;

import com.omvp.app.base.BaseFragmentModule;
import com.omvp.app.injector.scope.PerFragment;
import com.omvp.app.ui.samples.notice_dialog.presenter.SampleNoticePresenterModule;

import dagger.Binds;
import dagger.Module;

/**
 * Provides SampleNoticeFragment fragment dependencies.
 */
@Module(includes = {
        BaseFragmentModule.class,
        SampleNoticePresenterModule.class
})
public abstract class SampleNoticeFragmentModule {

    /**
     * As per the contract specified in {@link BaseFragmentModule}; "This must be included in all
     * fragment modules, which must provide a concrete implementation of {@link Fragment}.
     *
     * @param fragment the SampleNoticeFragment
     * @return the fragment
     */
    @Binds
    @PerFragment
    abstract Fragment fragment(SampleNoticeFragment fragment);

    @Binds
    @PerFragment
    abstract SampleNoticeView view(SampleNoticeFragment fragment);

}
