/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.view.view

import com.demo.movieapp.data.models.ErrorResponseModel
import com.demo.movieapp.data.models.HomeModel

/**
 * Created by Rene on 04.05.18..
 */
interface HomeView : DefaultView {

    fun movieFetchSuccess(item: HomeModel)

    fun movieFetchFailed(e: ErrorResponseModel)

    fun moviePaginationSuccess(item: HomeModel)

    fun searchMovieSuccess(item: HomeModel)

    fun searchMovieFailed(e: ErrorResponseModel)

}