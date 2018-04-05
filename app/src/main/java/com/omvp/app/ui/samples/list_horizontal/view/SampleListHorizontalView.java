package com.omvp.app.ui.samples.list_horizontal.view;

import android.view.View;

import com.omvp.app.base.mvp.view.BaseView;
import com.omvp.app.model.SampleModel;
import com.omvp.domain.SampleDomain;

import java.util.List;

public interface SampleListHorizontalView extends BaseView {
    void drawSampleList(List<Object> sampleModelList);

    void showEmptyView();

    void onSampleItemSelected(SampleDomain sampleDomain, View sharedView);

    void drawRemoveAnimation(int position);

    void drawAddAnimation(SampleModel model);
}
