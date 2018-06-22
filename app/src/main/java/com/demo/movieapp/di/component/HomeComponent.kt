/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.di.component

import com.demo.movieapp.di.AppScope
import com.demo.movieapp.di.module.HomeModule
import com.demo.movieapp.view.fragments.HomeFragment
import com.demo.movieapp.view.fragments.SearchFragment
import dagger.Component

/**
 * Created by Rene on 21.06.18.
 */
@AppScope
@Component(dependencies = [AppComponent::class], modules = [HomeModule::class])
interface HomeComponent {

    fun inject(homeFragment: HomeFragment)

    fun inject(searchFragment: SearchFragment)
}