package com.omvp.app.ui.samples.pager;


import com.omvp.app.ui.BaseTest;
import com.omvp.app.ui.samples.pager.presenter.SamplePagerPresenterImpl;
import com.omvp.app.ui.samples.pager.view.SamplePagerView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class BottomNavigationPresenterTest extends BaseTest {

    @Mock
    SamplePagerView view;

    private SamplePagerPresenterImpl presenter;


    @Before
    public void setup () throws Exception {
//        presenter = Mockito.spy(new SamplePagerPresenterImpl(view));

    }

    @Test
    public void check_presenter_not_null () {
//        assertNotNull(presenter);
    }


    @Test
    public void check_view_not_null () {
//        assertNotNull(view);
    }
}
