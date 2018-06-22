/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.view.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.demo.movieapp.R
import com.demo.movieapp.data.models.FavouriteSingleton
import com.demo.movieapp.data.models.HomeModel
import com.demo.movieapp.domain.listeners.FavouriteAdapterClickListener

/**
 * Created by Rene on 21.06.18.
 */
class BottomSheetAddToFavouriteAdapter (private var activity: Activity,
                                        private var itemMovie: HomeModel.MovieModel,
                                        private var clickListener: FavouriteAdapterClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var singleton = FavouriteSingleton.INSTANCE

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AddToFavouriteHolder(LayoutInflater.from(activity).inflate(R.layout.item_add_to_favourite_bottom_fragment, parent, false))
    }

    override fun getItemCount(): Int {
        return singleton.favouriteList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = singleton.favouriteList[position]

        holder as AddToFavouriteHolder

        holder.listTitleTextView.text = item.title
        holder.countTextView.text = item.items?.size.toString()

        if (!FavouriteSingleton.checkIfItemExistFavourite(itemMovie, item)) { //doesn't exist in wish list
            holder.addRelativeLayout.visibility = View.VISIBLE
        } else {
            holder.loadingRelativeLayout.visibility = View.VISIBLE
            holder.loadingProgressBar.visibility = View.INVISIBLE
            holder.checkMarkImageView.visibility = View.VISIBLE
        }

        holder.addRelativeLayout.setOnClickListener {
            clickListener.addToFavourite(item, holder)
        }
        holder.loadingRelativeLayout.setOnClickListener {
            clickListener.removeFromFavourite(item, holder)
        }

    }


    class AddToFavouriteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.itemMainRelativeLayout)
        lateinit var itemMainRelativeLayout: RelativeLayout

        @BindView(R.id.listTitleTextView)
        lateinit var listTitleTextView: TextView

        @BindView(R.id.countTextView)
        lateinit var countTextView: TextView

        @BindView(R.id.addRelativeLayout)
        lateinit var addRelativeLayout: RelativeLayout

        @BindView(R.id.loadingRelativeLayout)
        lateinit var loadingRelativeLayout: RelativeLayout

        @BindView(R.id.loadingProgressBar)
        lateinit var loadingProgressBar: ProgressBar

        @BindView(R.id.checkMarkImageView)
        lateinit var checkMarkImageView: ImageView

        init {
            ButterKnife.bind(this, itemView)
        }
    }

}