package com.omvp.app.ui.detail.view;

import android.app.Fragment;

import com.omvp.app.base.BaseFragmentModule;
import com.omvp.app.injector.scope.PerFragment;
import com.omvp.app.ui.detail.presenter.DetailPresenterModule;

import dagger.Binds;
import dagger.Module;

/**
 * Provides SampleMapFragment fragment dependencies.
 */
@Module(includes = {
        BaseFragmentModule.class,
        DetailPresenterModule.class
})
public abstract class DetailFragmentModule {

    /**
     * As per the contract specified in {@link BaseFragmentModule}; "This must be included in all
     * fragment modules, which must provide a concrete implementation of {@link Fragment}.
     *
     * @param fragment the SampleMapFragment
     * @return the fragment
     */
    @Binds
    @PerFragment
    abstract Fragment fragment(DetailFragment fragment);

    @Binds
    @PerFragment
    abstract DetailView view(DetailFragment fragment);

}
