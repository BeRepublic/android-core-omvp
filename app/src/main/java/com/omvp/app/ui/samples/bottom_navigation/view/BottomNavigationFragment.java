package com.omvp.app.ui.samples.bottom_navigation.view;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

import com.omvp.app.R;
import com.omvp.app.base.mvp.view.BaseViewFragment;
import com.omvp.app.base.mvp.view.BaseViewFragmentCallback;
import com.omvp.app.ui.samples.bottom_navigation.presenter.BottomNavigationPresenterImpl;

import butterknife.BindView;

public class BottomNavigationFragment extends BaseViewFragment<BottomNavigationPresenterImpl, BottomNavigationFragment.FragmentCallback>
        implements BottomNavigationView {

    @BindView(R.id.text)
    AppCompatTextView mTextView;
    @BindView(R.id.title)
    AppCompatTextView mTitleView;

    public interface FragmentCallback extends BaseViewFragmentCallback {

    }

    public static BottomNavigationFragment newInstance(Bundle bundle) {
        BottomNavigationFragment fragment = new BottomNavigationFragment();
        bundle = bundle == null ? new Bundle() : bundle;
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        setupViews();
    }


    @Override
    public void drawText(String text) {
        mTextView.setText(text);
    }

    @Override
    public void drawTitle(String title) {
        mTitleView.setText(title);
    }

    private void setupViews() {

    }
}
