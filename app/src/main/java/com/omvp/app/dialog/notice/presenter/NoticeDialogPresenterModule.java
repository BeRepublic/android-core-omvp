package com.omvp.app.dialog.notice.presenter;

import com.omvp.app.base.mvp.presenter.BasePresenterModule;
import com.omvp.app.injector.scope.PerFragment;

import dagger.Binds;
import dagger.Module;

/**
 * Provides NoticeDialogPresenterModule dependencies.
 */
@Module(includes = BasePresenterModule.class)
public abstract class NoticeDialogPresenterModule {

    @Binds
    @PerFragment
    abstract NoticeDialogPresenter dialogPresenter(NoticeDialogPresenterImpl presenter);

}
