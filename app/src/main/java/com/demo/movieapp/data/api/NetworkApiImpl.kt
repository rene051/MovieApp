/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.data.api

import com.demo.movieapp.data.models.HomeModel
import com.demo.movieapp.data.models.VideoModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Rene on 21.06.18.
 */
class NetworkApiImpl
@Inject
constructor(private val movieDatabaseApi: MovieDatabaseApi) : NetworkApi {

    override fun getPopularMovie(apiKey: String, page: Int): Single<HomeModel> {
        return Single.defer<HomeModel> { movieDatabaseApi.getPopularMovie(apiKey, page) }
    }

    override fun getNowPlayingMovie(apiKey: String, page: Int): Single<HomeModel> {
        return Single.defer<HomeModel> { movieDatabaseApi.getNowPlayingMovie(apiKey, page) }
    }

    override fun getTopRatedMovie(apiKey: String, page: Int): Single<HomeModel> {
        return Single.defer<HomeModel> { movieDatabaseApi.getTopRatedMovie(apiKey, page) }
    }

    override fun getUpcomingMovie(apiKey: String, page: Int): Single<HomeModel> {
        return Single.defer<HomeModel> { movieDatabaseApi.getUpcomingMovie(apiKey, page) }
    }

    override fun searchMovies(apiKey: String, search: String): Single<HomeModel> {
        return Single.defer<HomeModel> { movieDatabaseApi.searchMovie(apiKey, search) }
    }

    override fun getMovieVideos(movieId: String, apiKey: String): Single<VideoModel> {
        return Single.defer<VideoModel> { movieDatabaseApi.getMovieVideos(movieId, apiKey) }
    }
}