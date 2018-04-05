package com.omvp.app.ui.samples.bottom_navigation.presenter;


import com.omvp.app.ui.samples.bottom_navigation.view.BottomNavigationFirstFragment;
import com.omvp.app.ui.samples.bottom_navigation.view.BottomNavigationFirstView;

import javax.inject.Inject;

public class BottomNavigationFirstPresenterImpl extends BottomNavigationPresenterImpl<BottomNavigationFirstView> implements BottomNavigationFirstPresenter {

    @Inject
    public BottomNavigationFirstPresenterImpl(BottomNavigationFirstFragment view) {
        super(view);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();

        drawText("Fragment #1");
    }

    private void drawText(String text){
        if (mView != null){
            mView.drawText(text);
        }
    }
}
