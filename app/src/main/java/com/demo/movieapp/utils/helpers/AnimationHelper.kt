/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.utils.helpers

import android.app.Activity
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import com.demo.movieapp.R

/**
 * Created by Rene on 21.06.18.
 */
class AnimationHelper {

    companion object {

        fun enterAnimation(activity: FragmentActivity?) {
            activity?.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        }

        fun exitAnimation(activity: Activity) {
            activity.overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
        }

    }
}