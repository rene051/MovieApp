/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.presenter.impl

import android.content.Context
import com.demo.movieapp.domain.interactors.MainScreenInteractor
import com.demo.movieapp.presenter.MainScreenPresenter
import com.demo.movieapp.view.view.MainScreenView
import javax.inject.Inject

/**
 * Created by Rene on 21.06.18.
 */
class MainScreenPresenterImpl
@Inject
constructor(private val context: Context, private val mainScreenView: MainScreenView,
            private val mainScreenInteractor: MainScreenInteractor) : MainScreenPresenter{



}