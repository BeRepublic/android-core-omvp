package com.omvp.app.ui.samples.bottom_navigation.view;


import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.omvp.app.R;
import com.omvp.app.ui.samples.bottom_navigation.presenter.BottomNavigationFirstPresenterImpl;

import butterknife.BindView;
import butterknife.OnClick;

public class BottomNavigationFirstFragment
        extends BottomNavigationFragment<BottomNavigationFirstPresenterImpl, BottomNavigationFirstFragment.BottomNavigationFirstFragmentCallback>
        implements BottomNavigationFirstView {

    @BindView(R.id.text)
    AppCompatTextView mTextView;

    public interface BottomNavigationFirstFragmentCallback extends BottomNavigationFragment.BottomNavigationFragmentCallback {
        void onIncrementSelected(int position);
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

    @OnClick(R.id.counter_button)
    public void onCounterIncrementClicked(View view){
        mCallback.onIncrementSelected(0);
    }
}
