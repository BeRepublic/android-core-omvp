package com.omvp.app.ui.samples.bottom_navigation.view;

import com.omvp.app.base.mvp.view.BaseViewFragment;
import com.omvp.app.base.mvp.view.BaseViewFragmentCallback;
import com.omvp.app.ui.samples.bottom_navigation.presenter.BottomNavigationPresenter;

public abstract class BottomNavigationFragment<TPresenter extends BottomNavigationPresenter, TCallback extends BottomNavigationFragment.BottomNavigationFragmentCallback>
        extends BaseViewFragment<TPresenter, TCallback>
        implements BottomNavigationView {

    public interface BottomNavigationFragmentCallback extends BaseViewFragmentCallback {

    }

}
