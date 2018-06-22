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
import com.demo.movieapp.R
import com.demo.movieapp.data.api.APIConstants.Companion.IMAGE_BASE_URL
import com.demo.movieapp.data.models.HomeModel
import com.demo.movieapp.domain.listeners.HomeAdapterClickListener
import com.demo.movieapp.utils.AppConstants.Companion.LOADING_ITEM_TYPE
import com.demo.movieapp.utils.AppConstants.Companion.MOVIE_TYPE
import com.squareup.picasso.Picasso


/**
 * Created by Rene on 21.06.18.
 */
class HomeMainAdapter(private var activity: Activity,
                      private var movieList: ArrayList<HomeModel.MovieModel>,
                      private var clickListener: HomeAdapterClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var isLoadingAdded = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == MOVIE_TYPE) {
            MovieListHolder(LayoutInflater.from(activity).inflate(R.layout.item_home_movie, parent, false))
        } else {
            LoadingViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_loading, parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (movieList[position].id != null) {
            MOVIE_TYPE
        } else {
            LOADING_ITEM_TYPE
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)

        when (viewType) {
            MOVIE_TYPE -> {
                holder as MovieListHolder
                val item = movieList[position]

                holder.titleTextView.text = item.title
                Picasso.get().load(IMAGE_BASE_URL + item.backdropPath).fit().centerCrop().into(holder.homeItemImageView)
                holder.homeItemRelativeLayout.setOnClickListener {
                    clickListener.onItemClicked(item)
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return movieList.size
    }

    class MovieListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.homeItemRelativeLayout)
        lateinit var homeItemRelativeLayout: RelativeLayout

        @BindView(R.id.homeItemCardView)
        lateinit var homeItemCardView: CardView

        @BindView(R.id.homeItemImageView)
        lateinit var homeItemImageView: ImageView

        @BindView(R.id.titleTextView)
        lateinit var titleTextView: TextView

        init {
            ButterKnife.bind(this, itemView)
        }
    }


    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun add() {
        movieList.add(HomeModel.MovieModel())
        notifyItemInserted(movieList.size - 1)
    }

    fun remove() {

        val position = movieList.lastIndex
        movieList.removeAt(position)
        notifyItemRemoved(position)

    }

    fun clear() {
        isLoadingAdded = false
        while (itemCount > 0) {
            remove()
        }
    }

    fun isEmpty(): Boolean {
        return itemCount == 0
    }

    fun addLoadingFooter() {
        isLoadingAdded = true
        add()
    }

    fun removeLoadingFooter() {
        isLoadingAdded = false

        val position = if (movieList.size > 0) movieList.lastIndex else 0

        movieList.removeAt(position)
        notifyItemRemoved(position)

    }

}