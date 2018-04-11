package com.omvp.app.ui.samples.home.view;

import com.omvp.app.base.mvp.view.BaseView;
import com.omvp.app.ui.samples.home.presenter.HomePresenterImpl;

import java.util.List;

/**
 * Created by Angel on 21/02/2018.
 */
public interface HomeView extends BaseView {

    void itemSelected(HomePresenterImpl.SampleItemModel item);

    void drawItems(List<HomePresenterImpl.SampleItemModel> itemList);
}
