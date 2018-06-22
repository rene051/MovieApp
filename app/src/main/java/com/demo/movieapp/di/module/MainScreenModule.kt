/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.di.module

import com.demo.movieapp.di.AppScope
import com.demo.movieapp.domain.interactors.MainScreenInteractor
import com.demo.movieapp.domain.interactors.impl.MainScreenInteractorImpl
import com.demo.movieapp.presenter.MainScreenPresenter
import com.demo.movieapp.presenter.impl.MainScreenPresenterImpl
import com.demo.movieapp.view.view.MainScreenView
import dagger.Module
import dagger.Provides

/**
 * Created by Rene on 17.04.18..
 */
@Module
class MainScreenModule(private val mainScreenView: MainScreenView) {

    @AppScope
    @Provides
    fun providesMainScreenView(): MainScreenView = this.mainScreenView

    @AppScope
    @Provides
    fun providesMainScreenPresenter(presenter: MainScreenPresenterImpl): MainScreenPresenter = presenter

    @AppScope
    @Provides
    fun providesMainScreenInteractor(interactor: MainScreenInteractorImpl): MainScreenInteractor = interactor
}