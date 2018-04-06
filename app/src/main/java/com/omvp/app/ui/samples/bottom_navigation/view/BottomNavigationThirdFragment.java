package com.omvp.app.ui.samples.bottom_navigation.view;


import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.omvp.app.R;
import com.omvp.app.ui.samples.bottom_navigation.presenter.BottomNavigationThirdPresenterImpl;

import butterknife.BindView;
import butterknife.OnClick;

public class BottomNavigationThirdFragment
        extends BottomNavigationFragment<BottomNavigationThirdPresenterImpl, BottomNavigationThirdFragment.BottomNavigationThirdFragmentCallback>
        implements BottomNavigationThirdView {

    @BindView(R.id.text)
    AppCompatTextView mTextView;

    public interface BottomNavigationThirdFragmentCallback extends BottomNavigationFragment.BottomNavigationFragmentCallback {
        void onIncrementSelected(int position);
    }

    public BottomNavigationThirdFragment() {
    }

    public static BottomNavigationThirdFragment newInstance(Bundle bundle) {
        BottomNavigationThirdFragment fragment = new BottomNavigationThirdFragment();
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
        mCallback.onIncrementSelected(2);
    }
}
