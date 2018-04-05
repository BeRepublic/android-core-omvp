package com.omvp.app.ui.samples.bottom_navigation.view;


import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

import com.omvp.app.R;
import com.omvp.app.ui.samples.bottom_navigation.presenter.BottomNavigationSecondPresenterImpl;

import butterknife.BindView;

public class BottomNavigationSecondFragment
        extends BottomNavigationFragment<BottomNavigationSecondPresenterImpl, BottomNavigationSecondFragment.BottomNavigationSecondFragmentCallback>
        implements BottomNavigationSecondView {

    @BindView(R.id.text)
    AppCompatTextView mTextView;

    public interface BottomNavigationSecondFragmentCallback extends BottomNavigationFragment.BottomNavigationFragmentCallback {

    }

    public BottomNavigationSecondFragment() {
    }

    public static BottomNavigationSecondFragment newInstance(Bundle bundle) {
        BottomNavigationSecondFragment fragment = new BottomNavigationSecondFragment();
        bundle = bundle == null ? new Bundle() : bundle;
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void drawText(String text) {
        mTextView.setText(text);
    }
}
