/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.view

import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.demo.movieapp.R
import com.demo.movieapp.di.component.AppComponent
import com.demo.movieapp.App

/**
 * Created by Rene on 21.06.18.
 */
open class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        this.getApplicationComponent()?.inject(this)
    }

    protected fun addFragment(containerViewId: Int, fragment: Fragment) {

        val fragmentTransaction = this.fragmentManager.beginTransaction()
        fragmentTransaction.add(containerViewId, fragment)
        fragmentTransaction.commit()
    }

    protected fun getApplicationComponent(): AppComponent? {
        return (application as App).appComponent
    }
}