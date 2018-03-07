package com.omvp.app.ui.homekt.presenter


import com.omvp.app.base.mvp.presenter.BasePresenter
import com.omvp.app.ui.homekt.view.HomeView

import javax.inject.Inject

class HomePresenterImpl @Inject
internal constructor(homeView: HomeView) : BasePresenter<HomeView>(homeView), HomePresenter
