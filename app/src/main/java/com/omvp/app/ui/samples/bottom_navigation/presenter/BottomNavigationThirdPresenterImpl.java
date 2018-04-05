package com.omvp.app.ui.samples.bottom_navigation.presenter;


import com.omvp.app.ui.samples.bottom_navigation.view.BottomNavigationThirdFragment;
import com.omvp.app.ui.samples.bottom_navigation.view.BottomNavigationThirdView;

import javax.inject.Inject;

public class BottomNavigationThirdPresenterImpl extends BottomNavigationPresenterImpl<BottomNavigationThirdView> implements BottomNavigationThirdPresenter {

    @Inject
    public BottomNavigationThirdPresenterImpl(BottomNavigationThirdFragment view) {
        super(view);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();

        drawText("Fragment #3");
    }

    private void drawText(String text){
        if (mView != null){
            mView.drawText(text);
        }
    }
}
