/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.di.module

import com.demo.movieapp.di.AppScope
import com.demo.movieapp.domain.interactors.impl.HomeInteractorImpl
import com.demo.movieapp.domain.interactors.HomeInteractor
import com.demo.movieapp.presenter.HomePresenter
import com.demo.movieapp.presenter.impl.HomePresenterImpl
import com.demo.movieapp.view.view.HomeView
import dagger.Module
import dagger.Provides

/**
 * Created by Rene on 04.05.18..
 */
@Module
class HomeModule(private val homeView: HomeView) {

    @AppScope
    @Provides
    fun providesHomeView(): HomeView = this.homeView

    @AppScope
    @Provides
    fun providesHomePresenter(presenter: HomePresenterImpl): HomePresenter = presenter

    @AppScope
    @Provides
    fun providesHomeInteractor(interactor: HomeInteractorImpl): HomeInteractor = interactor

}