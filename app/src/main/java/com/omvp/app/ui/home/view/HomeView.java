package com.omvp.app.ui.home.view;

import com.omvp.app.base.mvp.view.BaseView;
import com.omvp.app.model.SampleItemModel;
import com.omvp.domain.SampleItem;

import java.util.List;

/**
 * Created by Angel on 21/02/2018.
 */
public interface HomeView extends BaseView {

    void itemSelected(SampleItem item);

    void drawItems(List<SampleItemModel> itemList);
}
