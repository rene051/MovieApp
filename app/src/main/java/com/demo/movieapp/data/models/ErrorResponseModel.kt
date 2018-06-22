/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Rene on 21.06.18.
 */
class ErrorResponseModel: Serializable {

    @SerializedName("code")
    var code: Int? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("messages")
    var messages: ArrayList<String>? = null

    constructor()

    constructor(code: Int?, title: String?, messages: ArrayList<String>?) {
        this.code = code
        this.title = title
        this.messages = messages
    }

}