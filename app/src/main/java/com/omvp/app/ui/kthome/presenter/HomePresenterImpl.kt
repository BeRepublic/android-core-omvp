package com.omvp.app.ui.kthome.presenter


import com.omvp.app.base.mvp.presenter.BasePresenter
import com.omvp.app.ui.kthome.view.HomeView

import javax.inject.Inject

class HomePresenterImpl @Inject
internal constructor(homeView: HomeView) : BasePresenter<HomeView>(homeView), HomePresenter
