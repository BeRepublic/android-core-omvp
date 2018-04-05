package com.omvp.app.suites;


import com.omvp.app.ui.samples.list.SampleListPresenterTest;
import com.omvp.app.ui.samples.multiple.SampleBottomPresenterTest;
import com.omvp.app.ui.samples.multiple.SampleTopPresenterTest;
import com.omvp.app.ui.samples.pager.BottomNavigationPresenterTest;
import com.omvp.app.ui.samples.sample.SampleLocalePresenterTest;
import com.omvp.app.ui.samples.take_picture.SampleTakePicturePresenterTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SampleLocalePresenterTest.class,
        SampleListPresenterTest.class,
        SampleBottomPresenterTest.class,
        SampleTopPresenterTest.class,
        BottomNavigationPresenterTest.class,
        SampleTakePicturePresenterTest.class

})
public class SamplesPresentersTestSuite {}
