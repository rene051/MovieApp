/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.di.component

import android.content.Context
import com.demo.movieapp.data.api.NetworkApi
import com.demo.movieapp.di.module.AppModule
import com.demo.movieapp.di.module.NetModule
import com.demo.movieapp.di.module.ThreadModule
import com.demo.movieapp.di.module.ThreadModule.Companion.OBSERVE_SCHEDULER
import com.demo.movieapp.di.module.ThreadModule.Companion.SUBSCRIBE_SCHEDULER
import com.demo.movieapp.view.BaseActivity
import com.demo.movieapp.view.BaseFragment
import dagger.Component
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Rene on 21.06.18.
 */
@Singleton
@Component(modules = [AppModule::class, NetModule::class, ThreadModule::class])
interface AppComponent {

    fun context(): Context

    fun networkApi(): NetworkApi

    @Named(OBSERVE_SCHEDULER)
    fun provideAndroidSchedulersMainThread(): Scheduler

    @Named(SUBSCRIBE_SCHEDULER)
    fun provideSchedulerIo(): Scheduler

    fun inject(baseActivity: BaseActivity)

    fun inject(baseFragment: BaseFragment)
}