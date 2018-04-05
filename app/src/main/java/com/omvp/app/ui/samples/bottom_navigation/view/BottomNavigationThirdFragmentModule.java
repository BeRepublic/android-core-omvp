package com.omvp.app.ui.samples.bottom_navigation.view;

import android.app.Fragment;

import com.omvp.app.base.BaseFragmentModule;
import com.omvp.app.injector.scope.PerFragment;
import com.omvp.app.ui.samples.bottom_navigation.presenter.BottomNavigationPresenterModule;

import dagger.Binds;
import dagger.Module;

/**
 * Provides SamplePagerFragment fragment dependencies.
 */
@Module(includes = {
        BaseFragmentModule.class,
        BottomNavigationPresenterModule.class
})
public abstract class BottomNavigationThirdFragmentModule {

    /**
     * As per the contract specified in {@link BaseFragmentModule}; "This must be included in all
     * fragment modules, which must provide a concrete implementation of {@link Fragment}.
     *
     * @param fragment the SamplePagerFragment
     * @return the fragment
     */

    @Binds
    @PerFragment
    abstract Fragment thirdFragment(BottomNavigationThirdFragment fragment);

    @Binds
    @PerFragment
    abstract BottomNavigationView thirdView(BottomNavigationThirdFragment fragment);
}
