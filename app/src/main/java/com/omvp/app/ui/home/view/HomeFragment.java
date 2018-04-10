package com.omvp.app.ui.home.view;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import com.omvp.app.R;
import com.omvp.app.base.mvp.view.BaseViewFragment;
import com.omvp.app.base.mvp.view.BaseViewFragmentCallback;
import com.omvp.app.model.SampleItemModel;
import com.omvp.app.ui.home.adapter.HomeListAdapter;
import com.omvp.app.ui.home.presenter.HomePresenter;
import com.omvp.domain.SampleItem;

import java.util.List;

import butterknife.BindView;

import static com.omvp.commons.Constants.AUTH_PHONE;
import static com.omvp.commons.Constants.BOTTOM_NAV;
import static com.omvp.commons.Constants.HORIZONTAL_LIST;
import static com.omvp.commons.Constants.INPUT;
import static com.omvp.commons.Constants.LIST;
import static com.omvp.commons.Constants.LOCALE;
import static com.omvp.commons.Constants.LOCATION;
import static com.omvp.commons.Constants.MULTIPLE_FRAGMENTS;
import static com.omvp.commons.Constants.NOTICE_DIALOG;
import static com.omvp.commons.Constants.PAGER;
import static com.omvp.commons.Constants.PICTURE;
import static com.omvp.commons.Constants.SOCIAL;
import static com.omvp.commons.Constants.VIBRATION;
import static com.omvp.commons.Constants.VIEW;

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

        void onNoticeDialogViewSelected ();

        void onBottomNavigationViewSelected();

        void onAuthPhoneViewSelected ();
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
    public void itemSelected(SampleItem item) {
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
    public void drawItems(List<SampleItemModel> itemList) {
        mAdapter.setItems(itemList);
    }

    private void setupViews() {
        mAdapter = new HomeListAdapter(mContext, (HomeListAdapter.AdapterCallback) mPresenter);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
    }
}
