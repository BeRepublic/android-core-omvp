package com.omvp.app.ui.samples.inputs.view;

import android.app.Fragment;

import com.omvp.app.base.BaseFragmentModule;
import com.omvp.app.injector.scope.PerFragment;
import com.omvp.app.ui.samples.inputs.presenter.SampleInputPresenterModule;

import dagger.Binds;
import dagger.Module;

/**
 * Provides SampleMapFragment fragment dependencies.
 */
@Module(includes = {
        BaseFragmentModule.class,
        SampleInputPresenterModule.class
})
public abstract class SampleInputFragmentModule {

    /**
     * As per the contract specified in {@link BaseFragmentModule}; "This must be included in all
     * fragment modules, which must provide a concrete implementation of {@link Fragment}.
     *
     * @param fragment the SampleMapFragment
     * @return the fragment
     */
    @Binds
    @PerFragment
    abstract Fragment fragment(SampleInputFragment fragment);

    @Binds
    @PerFragment
    abstract SampleInputView view(SampleInputFragment fragment);

}
