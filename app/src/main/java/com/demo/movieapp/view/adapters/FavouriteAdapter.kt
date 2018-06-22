/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.view.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.support.v4.content.ContextCompat
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
import com.demo.movieapp.data.models.FavouriteModel
import com.demo.movieapp.domain.listeners.FavouriteAdapterClickListener
import com.squareup.picasso.Picasso

/**
 * Created by Rene on 21.06.18.
 */
class FavouriteAdapter(private var activity: Activity,
                       private var favouriteList: ArrayList<FavouriteModel>,
                       private var clickListener: FavouriteAdapterClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AllFavouriteHolder(LayoutInflater.from(activity).inflate(R.layout.item_main_favourite, parent, false))
    }

    override fun getItemCount(): Int {
        return favouriteList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = favouriteList[position]

        holder as AllFavouriteHolder

        holder.favouriteItemTitle.text = item.title.toString()
        holder.favouriteItemCount.text = item.items?.size.toString()

        if (item.items!!.size == 0) {
            holder.favouriteImageOne.setImageDrawable(ContextCompat.getDrawable(activity, R.mipmap.ic_movie_logo))
            holder.cardViewOne.visibility = View.VISIBLE
        }

        if (item.items!!.size > 0) {
            holder.cardViewOne.visibility = View.VISIBLE
            Picasso.get().load(IMAGE_BASE_URL + item.items!![0].posterPath).fit().centerCrop().into(holder.favouriteImageOne)
        }

        if (item.items!!.size > 1) {
            holder.cardViewTwo.visibility = View.VISIBLE
            Picasso.get().load(IMAGE_BASE_URL + item.items!![1].posterPath).fit().centerCrop().into(holder.favouriteImageTwo)
        }

        if (item.items!!.size > 2) {
            holder.cardViewThree.visibility = View.VISIBLE
            Picasso.get().load(IMAGE_BASE_URL + item.items!![2].posterPath).fit().centerCrop().into(holder.favouriteImageThree)
        }

        if (item.items!!.size > 3) {
            holder.cardViewFour.visibility = View.VISIBLE
            Picasso.get().load(IMAGE_BASE_URL + item.items!![3].posterPath).fit().centerCrop().into(holder.favouriteImageFour)
        }

        holder.favouriteMainRelativeLayout.setOnClickListener {
            clickListener.onFavouriteClicked(item)
        }
    }

    class AllFavouriteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.favouriteMainRelativeLayout)
        lateinit var favouriteMainRelativeLayout: RelativeLayout

        @BindView(R.id.favouriteItemTitle)
        lateinit var favouriteItemTitle: TextView

        @BindView(R.id.favouriteItemCount)
        lateinit var favouriteItemCount: TextView

        @BindView(R.id.cardViewOne)
        lateinit var cardViewOne: CardView

        @BindView(R.id.favouriteImageOne)
        lateinit var favouriteImageOne: ImageView

        @BindView(R.id.cardViewTwo)
        lateinit var cardViewTwo: CardView

        @BindView(R.id.favouriteImageTwo)
        lateinit var favouriteImageTwo: ImageView

        @BindView(R.id.cardViewThree)
        lateinit var cardViewThree: CardView

        @BindView(R.id.favouriteImageThree)
        lateinit var favouriteImageThree: ImageView

        @BindView(R.id.cardViewFour)
        lateinit var cardViewFour: CardView

        @BindView(R.id.favouriteImageFour)
        lateinit var favouriteImageFour: ImageView

        init {
            ButterKnife.bind(this, itemView)
        }

    }
}