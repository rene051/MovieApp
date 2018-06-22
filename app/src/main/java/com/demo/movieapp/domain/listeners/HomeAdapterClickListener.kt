/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.domain.listeners

import com.demo.movieapp.data.models.HomeModel

/**
 * Created by Rene on 21.06.18.
 */
interface HomeAdapterClickListener {

    fun onItemClicked(item: HomeModel.MovieModel)
}