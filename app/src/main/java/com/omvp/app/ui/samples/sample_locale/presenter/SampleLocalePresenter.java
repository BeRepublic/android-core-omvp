package com.omvp.app.ui.samples.sample_locale.presenter;

import com.omvp.app.base.mvp.presenter.Presenter;

/**
 * Created by Angel on 21/02/2018.
 */

public interface SampleLocalePresenter extends Presenter {

    void localeSelected(int position);

    void changeLocale();

}
