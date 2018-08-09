/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.data.api

import com.demo.movieapp.data.api.APIConstants.Companion.API_KEY
import com.demo.movieapp.data.api.APIConstants.Companion.MOVIE_NOW_PLAYING
import com.demo.movieapp.data.api.APIConstants.Companion.MOVIE_POPULAR
import com.demo.movieapp.data.api.APIConstants.Companion.MOVIE_TOP_RATED
import com.demo.movieapp.data.api.APIConstants.Companion.MOVIE_UPCOMING
import com.demo.movieapp.data.api.APIConstants.Companion.PAGE
import com.demo.movieapp.data.api.APIConstants.Companion.SEARCH
import com.demo.movieapp.data.api.APIConstants.Companion.SEARCH_MOVIE
import com.demo.movieapp.data.models.HomeModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Rene on 21.06.18.
 */
interface MovieDatabaseApi {

    @GET(MOVIE_POPULAR)
    fun getPopularMovie(@Query(API_KEY) apiKey: String, @Query(PAGE) page: Int): Single<HomeModel>

    @GET(MOVIE_NOW_PLAYING)
    fun getNowPlayingMovie(@Query(API_KEY) apiKey: String, @Query(PAGE) page: Int): Single<HomeModel>

    @GET(MOVIE_TOP_RATED)
    fun getTopRatedMovie(@Query(API_KEY) apiKey: String, @Query(PAGE) page: Int): Single<HomeModel>

    @GET(MOVIE_UPCOMING)
    fun getUpcomingMovie(@Query(API_KEY) apiKey: String, @Query(PAGE) page: Int): Single<HomeModel>
    
    @GET(SEARCH_MOVIE)
    fun searchMovie(@Query(API_KEY) apiKey: String, @Query(SEARCH) search: String): Single<HomeModel>


}