/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.view.activities

import android.os.Bundle
import com.demo.movieapp.R
import com.demo.movieapp.view.BaseActivity
import android.content.Intent
import android.os.Handler
import com.demo.movieapp.utils.AppConstants.Companion.SPLASH_DISPLAY_LENGTH
import com.demo.movieapp.utils.helpers.AnimationHelper

/**
 * Created by Rene on 21.06.18.
 */
class SplashActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
                startActivity(Intent(this@SplashActivity, MainScreenActivity::class.java))
                AnimationHelper.enterAnimation(this)
                finish()
        }, SPLASH_DISPLAY_LENGTH.toLong())

    }

}