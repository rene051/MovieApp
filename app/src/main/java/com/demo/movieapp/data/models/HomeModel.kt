/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.data.models

import com.google.gson.annotations.SerializedName
import java.io.DataOutput
import java.io.Serializable

/**
 * Created by Rene on 22.06.18.
 */
class HomeModel : Serializable {

    @SerializedName("page")
    var page: Int? = null

    @SerializedName("total_result")
    var totalResult: Int? = null

    @SerializedName("total_pages")
    var totalPages: Int? = null

    @SerializedName("results")
    var result: ArrayList<MovieModel>? = null


    class MovieModel : Serializable {

        @SerializedName("vote_count")
        var voteCount: Int? = null

        @SerializedName("id")
        var id: Int? = null

        @SerializedName("video")
        var video: Boolean? = null

        @SerializedName("vote_average")
        var voteAverage: Double? = null

        @SerializedName("title")
        var title: String? = null

        @SerializedName("popularity")
        var popularity: Double? = null

        @SerializedName("poster_path")
        var posterPath: String? = null

        @SerializedName("original_language")
        var originalLanguage: String? = null

        @SerializedName("original_title")
        var originalTitle: String? = null

        @SerializedName("backdrop_path")
        var backdropPath: String? = null

        @SerializedName("overview")
        var overview: String? = null

        @SerializedName("release_date")
        var releaseDate: String? = null

        @SerializedName("key")
        var key: String? = null

    }

}