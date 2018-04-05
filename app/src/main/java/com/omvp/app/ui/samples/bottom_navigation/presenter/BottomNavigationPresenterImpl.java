
package com.omvp.app.ui.samples.bottom_navigation.presenter;


import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.ui.samples.bottom_navigation.view.BottomNavigationView;

public abstract class BottomNavigationPresenterImpl<TView extends BottomNavigationView>
        extends BasePresenter<TView>
        implements BottomNavigationPresenter {

    public BottomNavigationPresenterImpl(TView view) {
        super(view);
    }

}
