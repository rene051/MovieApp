/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.utils

/**
 * Created by Rene on 21.06.18.
 */
interface AppConstants {

    companion object {

        //Item types
        const val MOVIE_TYPE = 1
        const val LOADING_ITEM_TYPE = 2
        const val EMPTY_LIST = 3

        //Extras
        const val MOVIE_EXTRA = "MOVIE_EXTRA"

        //Timer
        const val SPLASH_DISPLAY_LENGTH = 3000

        //Flow
        const val EDIT_FAVOURITE_FLOW = "editFavouriteFlow"
        const val FAVOURITE_ACTIVITY = "favouriteActivity"
        const val FAVOURITE_ITEM = "FAVOURITE_ITEM"

        //Error types
        const val ERROR_NETWORK = "error_network"

        //Constants
        const val NOW_PLAYING = "NOW_PLAYING"
        const val POPULAR = "POPULAR"
        const val TOP_RATED = "TOP_RATED"
        const val UPCOMING = "UPCOMING"
    }
}