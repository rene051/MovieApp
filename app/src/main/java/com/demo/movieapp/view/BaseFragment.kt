/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.view

import android.os.Bundle
import android.support.v4.app.Fragment
import com.demo.movieapp.App
import com.demo.movieapp.di.component.AppComponent

/**
 * Created by Rene on 21.06.18.
 */
open class BaseFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getApplicationComponent()?.inject(this)

    }

    protected fun getApplicationComponent(): AppComponent? {
        return App.instance?.appComponent
    }
}