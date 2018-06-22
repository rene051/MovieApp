/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.data.models

/**
 * Created by Rene on 21.06.18.
 */
class FavouriteSingleton private constructor() {

    private object Holder {
        val INSTANCE = FavouriteSingleton()
    }

    companion object {
        val INSTANCE: FavouriteSingleton by lazy { Holder.INSTANCE }

        fun checkIfItemExistFavourite(i: HomeModel.MovieModel, item: FavouriteModel): Boolean {
            item.items?.forEach {
                if (i.id == it.id) {
                    return true
                }
            }
            return false
        }
    }

    var favouriteList = ArrayList<FavouriteModel>()
}