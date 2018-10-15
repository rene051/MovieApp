/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.data.api

import com.demo.movieapp.data.models.*
import io.reactivex.Single

/**
 * Created by Rene on 21.06.18.
 */
interface NetworkApi {

    fun getPopularMovie(apiKey: String, page: Int): Single<HomeModel>

    fun getNowPlayingMovie(apiKey: String, page: Int): Single<HomeModel>

    fun getTopRatedMovie(apiKey: String, page: Int): Single<HomeModel>

    fun getUpcomingMovie(apiKey: String, page: Int): Single<HomeModel>

    fun searchMovies(apiKey: String, search: String): Single<HomeModel>

    fun getMovieVideos(movieId: String, apiKey: String): Single<VideoModel>

}