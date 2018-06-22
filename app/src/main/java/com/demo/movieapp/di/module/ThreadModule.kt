/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.di.module

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Rene on 21.06.18.
 */
@Module
class ThreadModule {

    @Provides
    @Singleton
    @Named(OBSERVE_SCHEDULER)
    fun provideAndroidSchedulersMainThread(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    @Named(SUBSCRIBE_SCHEDULER)
    fun provideSchedulersIo(): Scheduler = Schedulers.io()

    companion object {

        const val OBSERVE_SCHEDULER = "ObserveScheduler"

        const val SUBSCRIBE_SCHEDULER = "SubscribeScheduler"
    }
}