package com.omvp.app.ui.samples.bottom_navigation.presenter;

import com.omvp.app.base.mvp.presenter.BasePresenterModule;
import com.omvp.app.injector.scope.PerFragment;

import dagger.Binds;
import dagger.Module;

/**
 * Provides SampleTopPresenterModule dependencies.
 */
@Module(includes = BasePresenterModule.class)
public abstract class BottomNavigationPresenterModule {

    @Binds
    @PerFragment
    abstract BottomNavigationPresenter presenter(BottomNavigationPresenterImpl presenter);

}