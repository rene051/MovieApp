package com.demo.movieapp.data.models

import java.io.Serializable

/**
 * Created by Rene on 22.06.18..
 */
class FavouriteModel : Serializable {

    var id: String? = null
    var title: String? = null
    var items: ArrayList<HomeModel.MovieModel>? = null
}