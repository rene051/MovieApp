/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.data.api

/**
 * Created by Rene on 21.06.18.
 */
interface APIConstants {

    companion object {

        const val BASE_URL = "https://api.themoviedb.org/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

        const val MOVIE_POPULAR = "3/movie/popular"
        const val SEARCH_MOVIE = "3/search/movie"

        const val apiKey = "c9f3368df47f1e3b16923681894bf09c"
        const val API_KEY = "api_key"
        const val PAGE = "page"
        const val SEARCH = "query"

    }
}