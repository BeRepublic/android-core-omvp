
package com.omvp.app.ui.home.presenter;


import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.base.reactivex.BaseDisposableMaybeObserver;
import com.omvp.app.model.SampleItemModel;
import com.omvp.app.model.mapper.SampleItemModelDataMapper;
import com.omvp.app.ui.home.adapter.HomeListAdapter;
import com.omvp.app.ui.home.view.HomeView;
import com.omvp.domain.SampleItem;
import com.omvp.domain.interactor.GetHomeListUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class HomePresenterImpl extends BasePresenter<HomeView> implements HomePresenter, HomeListAdapter.AdapterCallback {

    @Inject
    GetHomeListUseCase mGetHomeListUseCase;
    @Inject
    SampleItemModelDataMapper mSampleItemModelDataMapper;

    private List<SampleItem> mSampleItems;

    @Inject
    public HomePresenterImpl(HomeView homeView) {
        super(homeView);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();

        loadData();
    }

    @Override
    public void itemSelected(int position) {
        itemSelected(mSampleItems.get(position));
    }

    private void loadData() {
        mDisposableManager.add(mGetHomeListUseCase.execute()
                .map(new Function<List<SampleItem>, List<SampleItemModel>>() {
                    @Override
                    public List<SampleItemModel> apply(List<SampleItem> sampleItems) throws Exception {
                        mSampleItems = sampleItems;
                        return mSampleItemModelDataMapper.transform(sampleItems);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new BaseDisposableMaybeObserver<List<SampleItemModel>>(mContext) {
                    @Override
                    protected void onStart() {
                        super.onStart();
                        showProgress();
                    }

                    @Override
                    public void onSuccess(List<SampleItemModel> sampleItems) {
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

    private void itemSelected(SampleItem item) {
        if (mView != null) {
            mView.itemSelected(item);
        }
    }
}
