/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.presenter

/**
 * Created by Rene on 21.06.18.
 */
interface HomePresenter: BasePresenter {

    fun getPopularMovie(page: Int)

    fun getNowPlayingMovie(page: Int)

    fun getTopRatedMovie(page: Int)

    fun getUpcomingMovie(page: Int)

    fun searchMovies(search: String)

    fun getMovieVideo(movieId: Int?)

}