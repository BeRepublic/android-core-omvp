package com.omvp.app.ui.samples.bottom_navigation.presenter;

import com.omvp.app.base.mvp.presenter.BasePresenterModule;
import com.omvp.app.injector.scope.PerFragment;

import dagger.Binds;
import dagger.Module;

/**
 * Provides SamplePagerPresenterModule dependencies.
 */
@Module(includes = BasePresenterModule.class)
public abstract class BottomNavigationPresenterModule {

    @Binds
    @PerFragment
    abstract BottomNavigationFirstPresenter firstPresenter(BottomNavigationFirstPresenterImpl presenter);

    @Binds
    @PerFragment
    abstract BottomNavigationSecondPresenter secondPresenter(BottomNavigationSecondPresenterImpl presenter);

    @Binds
    @PerFragment
    abstract BottomNavigationThirdPresenter thirdPresenter(BottomNavigationThirdPresenterImpl presenter);
}