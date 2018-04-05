package com.omvp.app.ui.samples.pager.view;

import com.omvp.app.base.mvp.view.BaseViewFragment;
import com.omvp.app.base.mvp.view.BaseViewFragmentCallback;
import com.omvp.app.ui.samples.pager.presenter.SamplePagerPresenter;

public abstract class SamplePagerFragment<TPresenter extends SamplePagerPresenter, TCallback extends SamplePagerFragment.SamplePagerFragmentCallback>
        extends BaseViewFragment<TPresenter, TCallback>
        implements SamplePagerView {

    public interface SamplePagerFragmentCallback extends BaseViewFragmentCallback {

    }

}
