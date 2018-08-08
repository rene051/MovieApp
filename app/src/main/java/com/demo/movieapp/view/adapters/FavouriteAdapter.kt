/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.view.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.demo.movieapp.R
import com.demo.movieapp.data.models.FavouriteModel
import com.demo.movieapp.data.models.HomeModel
import com.demo.movieapp.domain.listeners.FavouriteAdapterClickListener

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

        if(item.items!!.size != 0) {
            holder.cardViewEmpty.visibility = View.GONE
        }

        val favouriteAdapter = FavouriteItemAdapter(activity, item.items!!)

        holder.favouriteRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        holder.favouriteRecyclerView.setHasFixedSize(true)
        holder.favouriteRecyclerView.adapter = favouriteAdapter

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

        @BindView(R.id.favouriteRecyclerView)
        lateinit var favouriteRecyclerView: RecyclerView

        @BindView(R.id.cardViewEmpty)
        lateinit var cardViewEmpty: CardView

        init {
            ButterKnife.bind(this, itemView)
        }

    }
}