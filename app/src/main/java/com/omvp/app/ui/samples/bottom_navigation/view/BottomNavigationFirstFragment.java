package com.omvp.app.ui.samples.bottom_navigation.view;


import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

import com.omvp.app.R;
import com.omvp.app.ui.samples.bottom_navigation.presenter.BottomNavigationFirstPresenterImpl;

import butterknife.BindView;

public class BottomNavigationFirstFragment
        extends BottomNavigationFragment<BottomNavigationFirstPresenterImpl, BottomNavigationFirstFragment.BottomNavigationFirstFragmentCallback>
        implements BottomNavigationFirstView {

    @BindView(R.id.text)
    AppCompatTextView mTextView;

    public interface BottomNavigationFirstFragmentCallback extends BottomNavigationFragment.BottomNavigationFragmentCallback {

    }

    public BottomNavigationFirstFragment() {
    }

    public static BottomNavigationFirstFragment newInstance(Bundle bundle) {
        BottomNavigationFirstFragment fragment = new BottomNavigationFirstFragment();
        bundle = bundle == null ? new Bundle() : bundle;
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void drawText(String text) {
        mTextView.setText(text);
    }
}
