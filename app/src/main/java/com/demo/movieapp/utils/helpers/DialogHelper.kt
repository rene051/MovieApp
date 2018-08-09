/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.utils.helpers

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import com.demo.movieapp.R
import android.view.Window
import android.widget.RelativeLayout
import android.widget.TextView

/**
 * Created by Rene on 21.06.18.
 */
class DialogHelper {

    companion object {

        fun oneButtonDialog(activity: Activity, title: String, message: String, cancelTitle: String,
                            cancelOnClickListener: DialogInterface.OnClickListener): Dialog {

            val dialog = AlertDialog.Builder(activity).setTitle(title)
                    .setMessage(message)
                    .setNegativeButton(cancelTitle, cancelOnClickListener)
                    .create()

            dialog.setOnShowListener {
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(activity, R.color.ColorPrimary))
            }

            return dialog
        }

    }
}