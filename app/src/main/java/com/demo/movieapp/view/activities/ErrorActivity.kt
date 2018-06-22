/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.view.activities

import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.cinnamon.utils.network.CinnamonNetwork
import com.demo.movieapp.R
import com.demo.movieapp.utils.AppConstants.Companion.ERROR_NETWORK
import com.demo.movieapp.utils.helpers.AnimationHelper
import kotlinx.android.synthetic.main.layout_no_internet.*

/**
 * Created by Rene on 21.06.18.
 */
class ErrorActivity : AppCompatActivity() {

    companion object {

        const val INTENT_EXTRA_ERROR = "intent_extra_error"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val string = intent.getStringExtra(INTENT_EXTRA_ERROR)
        when (intent.getStringExtra(INTENT_EXTRA_ERROR)) {

            ERROR_NETWORK -> {
                setContentView(R.layout.layout_no_internet)
                tryAgainNoInternetButton.setOnClickListener {
                    if (CinnamonNetwork.isNetworkAvailable(this)) {
                        finish()
                        AnimationHelper.exitAnimation(this)
                    }
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            onBackPressed()
        } else {
            // permission denied
        }
        return
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        AnimationHelper.exitAnimation(this)
    }
}
