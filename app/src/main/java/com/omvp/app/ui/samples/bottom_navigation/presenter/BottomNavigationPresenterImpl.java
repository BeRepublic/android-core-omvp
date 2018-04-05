
package com.omvp.app.ui.samples.bottom_navigation.presenter;


import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.ui.samples.bottom_navigation.view.BottomNavigationView;

import javax.inject.Inject;

public class BottomNavigationPresenterImpl extends BasePresenter<BottomNavigationView> implements BottomNavigationPresenter {

    @Inject
    public BottomNavigationPresenterImpl(BottomNavigationView bottomNavigationView) {
        super(bottomNavigationView);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();


    }
}
