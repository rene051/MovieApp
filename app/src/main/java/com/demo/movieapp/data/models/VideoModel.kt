package com.demo.movieapp.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class VideoModel : Serializable {

    @SerializedName("id")
    var id: Int? = null

    @SerializedName("results")
    var result: ArrayList<VideoModel.VideoItemModel>? = null

    class VideoItemModel : Serializable {

        @SerializedName("id")
        var id: String? = null

        @SerializedName("key")
        var key: String? = null
    }
}