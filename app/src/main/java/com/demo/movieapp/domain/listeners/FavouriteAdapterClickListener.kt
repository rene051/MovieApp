/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.domain.listeners

import com.demo.movieapp.data.models.FavouriteModel
import com.demo.movieapp.data.models.HomeModel
import com.demo.movieapp.view.adapters.BottomSheetAddToFavouriteAdapter

/**
 * Created by Rene on 21.06.18.
 */
interface FavouriteAdapterClickListener {

    fun addToFavourite(item: FavouriteModel, holder: BottomSheetAddToFavouriteAdapter.AddToFavouriteHolder)

    fun removeFromFavourite(item: FavouriteModel, holder: BottomSheetAddToFavouriteAdapter.AddToFavouriteHolder)

    fun onFavouriteClicked(item: FavouriteModel)

    fun onFavouriteSectionClicked(item: HomeModel.MovieModel)
}