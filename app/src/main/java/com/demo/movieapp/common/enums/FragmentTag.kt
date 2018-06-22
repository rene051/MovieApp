/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.common.enums

/**
 * Created by Rene on 21.06.18.
 */
enum class FragmentTag
constructor(fragmentTag: String) {

    HomeFragment("HomeFragment"),
    FavouriteFragment("FavouriteFragment"),
    SearchFragment("SearchFragment"),
    FavouriteSectionFragment("FavouriteSectionFragment");

    private var fragmentTag: String = ""

    init {
        this.fragmentTag = fragmentTag
    }

    fun getTag(): String {
        return fragmentTag
    }

}