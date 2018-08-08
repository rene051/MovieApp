package com.demo.movieapp.view.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.demo.movieapp.R
import com.demo.movieapp.data.api.APIConstants.Companion.IMAGE_BASE_URL
import com.demo.movieapp.data.models.HomeModel
import com.squareup.picasso.Picasso

class FavouriteItemAdapter(private var activity: Activity,
                           private var movieList: ArrayList<HomeModel.MovieModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FavouriteItemHolder(LayoutInflater.from(activity).inflate(R.layout.item_single_main_favourite, parent, false))
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = movieList[position]

        holder as FavouriteItemHolder

        if (movieList.size > 0) {
            Picasso.get().load(IMAGE_BASE_URL + item.posterPath).fit().centerCrop().into(holder.favouriteImageItem)
        }

    }

    class FavouriteItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.cardViewItem)
        lateinit var cardViewItem: CardView

        @BindView(R.id.favouriteImageItem)
        lateinit var favouriteImageItem: ImageView

        init {
            ButterKnife.bind(this, itemView)
        }

    }
}