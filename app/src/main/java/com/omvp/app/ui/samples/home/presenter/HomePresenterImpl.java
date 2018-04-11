
package com.omvp.app.ui.samples.home.presenter;


import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.base.reactivex.BaseDisposableMaybeObserver;
import com.omvp.app.ui.samples.home.adapter.HomeListAdapter;
import com.omvp.app.ui.samples.home.view.HomeView;
import com.omvp.commons.DontObfuscate;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lombok.Data;

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

public class HomePresenterImpl extends BasePresenter<HomeView> implements HomePresenter, HomeListAdapter.AdapterCallback {

    private static final int NUM_ITEMS = 14;

    private static List<SampleItemModel> sampleItemList = new ArrayList<>(NUM_ITEMS);

    private static void init() {
        if (sampleItemList.isEmpty()) {
            sampleItemList.add(initSampleItem(VIEW, "A sample view"));
            sampleItemList.add(initSampleItem(LIST, "A sample list view"));
            sampleItemList.add(initSampleItem(HORIZONTAL_LIST, "A sample list view with nested horizontal list"));
            sampleItemList.add(initSampleItem(PAGER, "A sample pager view"));
            sampleItemList.add(initSampleItem(MULTIPLE_FRAGMENTS, "A sample view with multiple fragments"));
            sampleItemList.add(initSampleItem(LOCATION, "A sample view to show device location"));
            sampleItemList.add(initSampleItem(PICTURE, "A sample view to take or pick up photos"));
            sampleItemList.add(initSampleItem(LOCALE, "A sample view to change device language"));
            sampleItemList.add(initSampleItem(VIBRATION, "A sample view to use device vibration"));
            sampleItemList.add(initSampleItem(INPUT, "A view with input layouts"));
            sampleItemList.add(initSampleItem(SOCIAL, "A view with socials connection"));
            sampleItemList.add(initSampleItem(NOTICE_DIALOG, "A view with Notice Dialog"));
            sampleItemList.add(initSampleItem(BOTTOM_NAV, "A view with a bottom bar navigation"));
            sampleItemList.add(initSampleItem(AUTH_PHONE, "A view with check phone number validation"));
        }
    }

    private static SampleItemModel initSampleItem(String type, String title) {
        SampleItemModel item = new SampleItemModel();
        item.setType(type);
        item.setTitle(title);
        return item;
    }

    @Inject
    public HomePresenterImpl(HomeView homeView) {
        super(homeView);

        init();
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();

        loadData();
    }

    @Override
    public void itemSelected(int position) {
        itemSelected(sampleItemList.get(position));
    }

    private void loadData() {
        mDisposableManager.add(Maybe.just(sampleItemList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new BaseDisposableMaybeObserver<List<SampleItemModel>>(mContext) {
                    @Override
                    protected void onStart() {
                        super.onStart();
                        showProgress();
                    }

                    @Override
                    public void onSuccess(List<HomePresenterImpl.SampleItemModel> sampleItems) {
                        hideProgress();
                        drawItems(sampleItems);
                    }

                    @Override
                    public void onComplete() {
                        hideProgress();
                    }

                    @Override
                    protected void onError(int code, String title, String description) {
                        hideProgress();
                        showError(code, title, description);
                    }
                }));
    }

    private void drawItems(List<SampleItemModel> itemList) {
        if (mView != null) {
            mView.drawItems(itemList);
        }
    }

    private void itemSelected(SampleItemModel item) {
        if (mView != null) {
            mView.itemSelected(item);
        }
    }

    @Data
    @Parcel
    @DontObfuscate
    public static class SampleItemModel {

        String title;
        String type;

        public SampleItemModel() {

        }

    }
}
