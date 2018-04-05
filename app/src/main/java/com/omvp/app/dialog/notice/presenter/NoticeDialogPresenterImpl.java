package com.omvp.app.dialog.notice.presenter;

import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.dialog.notice.view.NoticeDialogView;

import javax.inject.Inject;

public class NoticeDialogPresenterImpl extends BasePresenter<NoticeDialogView> implements NoticeDialogPresenter {

    @Inject
    public NoticeDialogPresenterImpl(NoticeDialogView noticeDialogView) {
        super(noticeDialogView);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();
    }

}
