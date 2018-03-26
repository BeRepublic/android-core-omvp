package com.omvp.app.ui.samples.sample_locale.view;

import com.omvp.app.base.mvp.view.BaseView;

import java.util.List;

/**
 * Created by Angel on 21/02/2018.
 */
public interface SampleLocaleView extends BaseView {

    void drawLocale(String locale);

    void drawLocaleSelector(List<String> localeList, int selection);

}
