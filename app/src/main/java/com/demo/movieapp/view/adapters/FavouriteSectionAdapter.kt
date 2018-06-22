/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.view.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.cinnamon.utils.date.CinnamonDate
import com.demo.movieapp.R
import com.demo.movieapp.data.api.APIConstants.Companion.IMAGE_BASE_URL
import com.demo.movieapp.data.models.HomeModel
import com.demo.movieapp.domain.listeners.FavouriteAdapterClickListener
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList

/**
 * Created by Rene on 21.06.18.
 */
class FavouriteSectionAdapter(private var activity: Activity,
                              private var movieList: ArrayList<HomeModel.MovieModel>,
                              private var clickListener: FavouriteAdapterClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FavouriteViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_favourite_section, parent, false))
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as FavouriteViewHolder

        val item = movieList[position]

        Picasso.get().load(IMAGE_BASE_URL + item.posterPath).fit().centerCrop().into(holder.favouriteImageView)
        holder.titleTextView.text = item.title
        holder.ratingTextView.text = item.voteAverage.toString()
        if(item.releaseDate != null && item.releaseDate!!.isNotEmpty()) {
            holder.yearTextView.text = CinnamonDate.convertDate(item.releaseDate, "YYYY-MM-dd", "dd MMM YYYY")
        }
        holder.mainFavouriteItemRelativeLayout.setOnClickListener {
            clickListener.onFavouriteSectionClicked(item)
        }

    }

    class FavouriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.mainFavouriteItemRelativeLayout)
        lateinit var mainFavouriteItemRelativeLayout: RelativeLayout

        @BindView(R.id.titleTextView)
        lateinit var titleTextView: TextView

        @BindView(R.id.ratingTextView)
        lateinit var ratingTextView: TextView

        @BindView(R.id.yearTextView)
        lateinit var yearTextView: TextView

        @BindView(R.id.favouriteImageView)
        lateinit var favouriteImageView: ImageView


        init {
            ButterKnife.bind(this, itemView)
        }

    }
}