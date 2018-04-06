package com.omvp.app.ui.samples.bottom_navigation.view;


import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.omvp.app.R;
import com.omvp.app.ui.samples.bottom_navigation.presenter.BottomNavigationSecondPresenterImpl;

import butterknife.BindView;
import butterknife.OnClick;

public class BottomNavigationSecondFragment
        extends BottomNavigationFragment<BottomNavigationSecondPresenterImpl, BottomNavigationSecondFragment.BottomNavigationSecondFragmentCallback>
        implements BottomNavigationSecondView {

    @BindView(R.id.text)
    AppCompatTextView mTextView;

    public interface BottomNavigationSecondFragmentCallback extends BottomNavigationFragment.BottomNavigationFragmentCallback {
        void onIncrementSelected(int position);
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

    @OnClick(R.id.counter_button)
    public void onCounterIncrementClicked(View view){
        mCallback.onIncrementSelected(1);
    }
}
