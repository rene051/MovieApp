/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.utils.helpers

import android.app.Activity
import android.content.DialogInterface
import android.support.v4.app.FragmentActivity
import android.view.inputmethod.InputMethodManager
import com.demo.movieapp.R
import com.demo.movieapp.data.models.ErrorResponseModel
import com.google.gson.Gson
import retrofit2.HttpException
import java.net.NoRouteToHostException
import java.net.SocketTimeoutException

/**
 * Created by Rene on 21.06.18.
 */
class ErrorCodeHelper {

    companion object {

        fun castErrorResponse(e: Throwable): ErrorResponseModel {

            return if (e is SocketTimeoutException || e is NoRouteToHostException) {
                ErrorResponseModel()
            } else {
                val error = e as HttpException
                val errorBody = error.response().errorBody()!!.string()
                castErrorBody(errorBody)
            }
        }

        fun castErrorBody(errorBody: String): ErrorResponseModel {
            return if (errorBody != "") {
                Gson().fromJson(errorBody, ErrorResponseModel::class.java)
            } else {
                var array: ArrayList<String> = ArrayList()
                array.add("error")
                ErrorResponseModel(401, "error", array)
            }
        }

        fun handleResponseError(activity: FragmentActivity, e: ErrorResponseModel) {

            if (activity.currentFocus != null) {
                val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(activity.currentFocus.windowToken, 0)
            }

            DialogHelper.oneButtonDialog(activity, e.title.toString(), e.messages!![0],
                    activity.getString(R.string.ok), DialogInterface.OnClickListener { _, _ -> }).show()
        }
    }

}