package com.omvp.app.ui.samples.home.view;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import com.omvp.app.R;
import com.omvp.app.base.mvp.view.BaseViewFragment;
import com.omvp.app.base.mvp.view.BaseViewFragmentCallback;
import com.omvp.app.ui.samples.home.adapter.HomeListAdapter;
import com.omvp.app.ui.samples.home.presenter.HomePresenter;
import com.omvp.app.ui.samples.home.presenter.HomePresenterImpl;

import java.util.List;

import butterknife.BindView;

import static com.omvp.app.ui.samples.home.presenter.HomePresenterImpl.AUTH_PHONE;
import static com.omvp.app.ui.samples.home.presenter.HomePresenterImpl.BOTTOM_NAV;
import static com.omvp.app.ui.samples.home.presenter.HomePresenterImpl.HORIZONTAL_LIST;
import static com.omvp.app.ui.samples.home.presenter.HomePresenterImpl.INPUT;
import static com.omvp.app.ui.samples.home.presenter.HomePresenterImpl.LIST;
import static com.omvp.app.ui.samples.home.presenter.HomePresenterImpl.LOCALE;
import static com.omvp.app.ui.samples.home.presenter.HomePresenterImpl.LOCATION;
import static com.omvp.app.ui.samples.home.presenter.HomePresenterImpl.MULTIPLE_FRAGMENTS;
import static com.omvp.app.ui.samples.home.presenter.HomePresenterImpl.NOTICE_DIALOG;
import static com.omvp.app.ui.samples.home.presenter.HomePresenterImpl.PAGER;
import static com.omvp.app.ui.samples.home.presenter.HomePresenterImpl.PICTURE;
import static com.omvp.app.ui.samples.home.presenter.HomePresenterImpl.SOCIAL;
import static com.omvp.app.ui.samples.home.presenter.HomePresenterImpl.VIBRATION;
import static com.omvp.app.ui.samples.home.presenter.HomePresenterImpl.VIEW;

public class HomeFragment extends BaseViewFragment<HomePresenter, HomeFragment.FragmentCallback>
        implements HomeView {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private HomeListAdapter mAdapter;

    public interface FragmentCallback extends BaseViewFragmentCallback {
        void onSampleViewSelected();

        void onSampleListSelected();

        void onSamplePagerSelected();

        void onSampleMultipleSelected();

        void onSampleLocationSelected();

        void onSampleTakePictureSelected();

        void onSampleLocaleSelected();

        void onSampleHorizontalListClicked();

        void onVibrationSelected();

        void onInputViewSelected();

        void onSocialViewSelected();

        void onNoticeDialogViewSelected();

        void onBottomNavigationViewSelected();

        void onAuthPhoneViewSelected();
    }

    public static HomeFragment newInstance(Bundle bundle) {
        HomeFragment fragment = new HomeFragment();
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
    public void itemSelected(HomePresenterImpl.SampleItemModel item) {
        switch (item.getType()) {
            case VIEW:
                mCallback.onSampleViewSelected();
                break;
            case LIST:
                mCallback.onSampleListSelected();
                break;
            case HORIZONTAL_LIST:
                mCallback.onSampleHorizontalListClicked();
                break;
            case PAGER:
                mCallback.onSamplePagerSelected();
                break;
            case MULTIPLE_FRAGMENTS:
                mCallback.onSampleMultipleSelected();
                break;
            case LOCATION:
                mCallback.onSampleLocationSelected();
                break;
            case PICTURE:
                mCallback.onSampleTakePictureSelected();
                break;
            case LOCALE:
                mCallback.onSampleLocaleSelected();
                break;
            case VIBRATION:
                mCallback.onVibrationSelected();
                break;
            case INPUT:
                mCallback.onInputViewSelected();
                break;
            case SOCIAL:
                mCallback.onSocialViewSelected();
                break;
            case NOTICE_DIALOG:
                mCallback.onNoticeDialogViewSelected();
                break;
            case BOTTOM_NAV:
                mCallback.onBottomNavigationViewSelected();
                break;
            case AUTH_PHONE:
                mCallback.onAuthPhoneViewSelected();
                break;
        }
    }

    @Override
    public void drawItems(List<HomePresenterImpl.SampleItemModel> itemList) {
        mAdapter.setItems(itemList);
    }

    private void setupViews() {
        mAdapter = new HomeListAdapter(mContext, (HomeListAdapter.AdapterCallback) mPresenter);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
    }
}
