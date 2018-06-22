/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.view.fragments

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.movieapp.R
import com.demo.movieapp.data.models.FavouriteModel
import com.demo.movieapp.data.models.FavouriteSingleton
import com.demo.movieapp.data.models.HomeModel
import com.demo.movieapp.domain.listeners.FavouriteAdapterClickListener
import com.demo.movieapp.utils.AppConstants.Companion.EDIT_FAVOURITE_FLOW
import com.demo.movieapp.utils.AppConstants.Companion.FAVOURITE_ACTIVITY
import com.demo.movieapp.utils.AppConstants.Companion.MOVIE_EXTRA
import com.demo.movieapp.utils.helpers.AnimationHelper
import com.demo.movieapp.view.activities.AddNewFavouriteActivity
import com.demo.movieapp.view.adapters.BottomSheetAddToFavouriteAdapter
import kotlinx.android.synthetic.main.bottom_sheet_add_to_favourite.*

/**
 * Created by Rene on 21.06.18.
 */
class BottomSheetAddFavouriteFragment : BottomSheetDialogFragment(), FavouriteAdapterClickListener {

    private lateinit var favouriteAdapter: BottomSheetAddToFavouriteAdapter
    private var singleton = FavouriteSingleton.INSTANCE
    private lateinit var itemMovie: HomeModel.MovieModel
    private lateinit var favouriteList: ArrayList<FavouriteModel>

    companion object {
        fun newInstance(item: HomeModel.MovieModel): BottomSheetAddFavouriteFragment {
            val bundle = Bundle()
            bundle.putSerializable(MOVIE_EXTRA, item)
            val fragment = BottomSheetAddFavouriteFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.bottom_sheet_add_to_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemMovie = arguments?.getSerializable(MOVIE_EXTRA) as HomeModel.MovieModel

        clickListeners()
        setAdapter()

    }

    override fun onResume() {
        super.onResume()

        favouriteList = singleton.favouriteList
        favouriteAdapter.notifyDataSetChanged()
    }

    private fun clickListeners() {
        favouriteRelativeLayout.setOnClickListener {
            startActivity(Intent(requireActivity(), AddNewFavouriteActivity::class.java)
                    .putExtra(EDIT_FAVOURITE_FLOW, FAVOURITE_ACTIVITY))
            AnimationHelper.enterAnimation(requireActivity())
        }

        doneTextView.setOnClickListener {
            this.dismiss()
        }
    }

    private fun setAdapter() {
        favouriteList = singleton.favouriteList
        favouriteAdapter = BottomSheetAddToFavouriteAdapter(requireActivity(), itemMovie, this)

        bottomFragmentWishListRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        bottomFragmentWishListRecyclerView.setHasFixedSize(true)
        bottomFragmentWishListRecyclerView.adapter = favouriteAdapter
    }

    override fun addToFavourite(item: FavouriteModel, holder: BottomSheetAddToFavouriteAdapter.AddToFavouriteHolder) {
        holder.addRelativeLayout.visibility = View.INVISIBLE
        holder.loadingRelativeLayout.visibility = View.VISIBLE
        holder.checkMarkImageView.visibility = View.VISIBLE
        holder.countTextView.text = (holder.countTextView.text.toString().toInt() + 1).toString()
        item.items?.add(itemMovie)
    }

    override fun removeFromFavourite(item: FavouriteModel, holder: BottomSheetAddToFavouriteAdapter.AddToFavouriteHolder) {
        holder.addRelativeLayout.visibility = View.VISIBLE
        holder.loadingRelativeLayout.visibility = View.INVISIBLE
        holder.checkMarkImageView.visibility = View.INVISIBLE
        holder.countTextView.text = (holder.countTextView.text.toString().toInt() - 1).toString()

        val iterator = item.items?.iterator()
        while(iterator!!.hasNext()) {
            if (iterator.next().id == itemMovie.id) {
                iterator.remove()
            }
        }
    }

    override fun onFavouriteClicked(item: FavouriteModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFavouriteSectionClicked(item: HomeModel.MovieModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}