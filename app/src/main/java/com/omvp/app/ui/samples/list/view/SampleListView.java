package com.omvp.app.ui.samples.list.view;

import android.view.View;

import com.omvp.app.base.mvp.view.BaseView;
import com.omvp.app.model.SampleModel;
import com.omvp.domain.SampleDomain;

import java.util.List;

public interface SampleListView extends BaseView {
    void drawSampleList(List<SampleModel> sampleModelList);

    void showEmptyView();

    void onSampleItemSelected(SampleDomain sampleDomain, View sharedView);

    void drawRemoveAnimation(int position);

    void drawAddAnimation(SampleModel model);

    void drawViewMoved(int oldPosition, int newPosition);

    void drawViewSwiped(int position);
}
