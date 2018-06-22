/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.di.component

import com.demo.movieapp.di.AppScope
import com.demo.movieapp.di.module.MainScreenModule
import com.demo.movieapp.view.activities.MainScreenActivity
import dagger.Component

/**
 * Created by Rene on 17.04.18..
 */
@AppScope
@Component(dependencies = [AppComponent::class], modules = [MainScreenModule::class])
interface MainScreenComponent {

    fun inject(mainScreenActivity: MainScreenActivity)

}