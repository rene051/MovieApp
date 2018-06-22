/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp

import android.app.Application
import com.demo.movieapp.di.component.AppComponent
import com.demo.movieapp.di.component.DaggerAppComponent
import com.demo.movieapp.di.module.AppModule

/**
 * Created by Rene on 21.06.18.
 */
class App : Application() {

    var appComponent: AppComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()

        instance = this

        this.appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    companion object {
        var instance: App? = null
    }
}