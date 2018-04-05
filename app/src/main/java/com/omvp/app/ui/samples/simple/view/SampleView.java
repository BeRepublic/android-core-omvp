package com.omvp.app.ui.samples.simple.view;

import com.omvp.app.base.mvp.view.BaseView;

/**
 * Created by Angel on 21/02/2018.
 */
public interface SampleView extends BaseView {
    void drawText(String text);

    void drawTitle(String title);
}
