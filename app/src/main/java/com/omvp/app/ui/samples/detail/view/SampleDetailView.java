package com.omvp.app.ui.samples.detail.view;

import com.omvp.app.base.mvp.view.BaseView;

/**
 * Created by Angel on 21/02/2018.
 */
public interface SampleDetailView extends BaseView {

    void drawImage(int imageRes);

    void drawText(String text);

    void drawTitle(String title);

}
