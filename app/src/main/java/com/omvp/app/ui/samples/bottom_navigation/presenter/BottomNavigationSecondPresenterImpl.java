package com.omvp.app.ui.samples.bottom_navigation.presenter;


import com.omvp.app.ui.samples.bottom_navigation.view.BottomNavigationSecondFragment;
import com.omvp.app.ui.samples.bottom_navigation.view.BottomNavigationSecondView;

import javax.inject.Inject;

public class BottomNavigationSecondPresenterImpl extends BottomNavigationPresenterImpl<BottomNavigationSecondView> implements BottomNavigationSecondPresenter {

    @Inject
    public BottomNavigationSecondPresenterImpl(BottomNavigationSecondFragment view) {
        super(view);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();

        drawText("Fragment #2");
    }

    private void drawText(String text){
        if (mView != null){
            mView.drawText(text);
        }
    }
}
