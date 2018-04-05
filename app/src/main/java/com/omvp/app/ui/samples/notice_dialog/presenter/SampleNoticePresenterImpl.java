
package com.omvp.app.ui.samples.notice_dialog.presenter;


import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.ui.samples.notice_dialog.view.SampleNoticeView;

import javax.inject.Inject;

public class SampleNoticePresenterImpl extends BasePresenter<SampleNoticeView>
        implements SampleNoticePresenter{

    @Inject
    public SampleNoticePresenterImpl(SampleNoticeView sampleNoticetView) {
        super(sampleNoticetView);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();
        showDialog ();
    }

    private void showDialog() {
        mView.showError(200, "Test Dialog", "This is a sample message to test the dialog");
    }

}
