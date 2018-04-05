
package com.omvp.app.ui.samples.pager.presenter;


import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.ui.samples.pager.view.SamplePagerView;

public abstract class SamplePagerPresenterImpl<TView extends SamplePagerView>
        extends BasePresenter<TView>
        implements SamplePagerPresenter {

    public SamplePagerPresenterImpl(TView view) {
        super(view);
    }

}
