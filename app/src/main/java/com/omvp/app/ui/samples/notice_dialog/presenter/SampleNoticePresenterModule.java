package com.omvp.app.ui.samples.notice_dialog.presenter;

import com.omvp.app.base.mvp.presenter.BasePresenterModule;
import com.omvp.app.injector.scope.PerFragment;

import dagger.Binds;
import dagger.Module;

/**
 * Provides SampleListPresenterModule dependencies.
 */
@Module(includes = BasePresenterModule.class)
public abstract class SampleNoticePresenterModule {

    @Binds
    @PerFragment
    abstract SampleNoticePresenter presenter(SampleNoticePresenterImpl presenter);

}
