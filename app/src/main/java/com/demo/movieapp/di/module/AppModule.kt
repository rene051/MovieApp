/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Rene on 28.03.18..
 */
@Module
class AppModule(private val mApplication: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Context {
        return this.mApplication
    }
}