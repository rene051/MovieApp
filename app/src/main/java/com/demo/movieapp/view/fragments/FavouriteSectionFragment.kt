/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.view.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.demo.movieapp.R
import com.demo.movieapp.data.models.*
import com.demo.movieapp.domain.listeners.FavouriteAdapterClickListener
import com.demo.movieapp.utils.AppConstants
import com.demo.movieapp.utils.AppConstants.Companion.FAVOURITE_ITEM
import com.demo.movieapp.utils.helpers.AnimationHelper
import com.demo.movieapp.view.BaseFragment
import com.demo.movieapp.view.activities.MovieItemActivity
import com.demo.movieapp.view.adapters.BottomSheetAddToFavouriteAdapter
import com.demo.movieapp.view.adapters.FavouriteSectionAdapter
import kotlinx.android.synthetic.main.fragment_favourite_section.*

/**
 * Created by Rene on 21.06.18.
 */
class FavouriteSectionFragment: BaseFragment(), FavouriteAdapterClickListener {

    private lateinit var favouriteItem : FavouriteModel
    private lateinit var favouriteAdapter: FavouriteSectionAdapter
    var singleton = FavouriteSingleton.INSTANCE

    companion object {
        fun newInstance(item: FavouriteModel): FavouriteSectionFragment{
            val bundle = Bundle()
            bundle.putSerializable(FAVOURITE_ITEM, item)
            val fragment = FavouriteSectionFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_favourite_section, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments
        if (bundle != null) {
            favouriteItem = bundle.getSerializable(FAVOURITE_ITEM) as FavouriteModel
        }

        (activity as AppCompatActivity).supportActionBar!!.title = favouriteItem.title
        (activity as AppCompatActivity).supportActionBar?.elevation = 8F

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)

        setAdapter()
    }

    override fun onResume() {
        super.onResume()
        favouriteAdapter.notifyDataSetChanged()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setAdapter() {
        favouriteAdapter = FavouriteSectionAdapter(requireActivity(), favouriteItem.items!!, this)

        favouriteSectionRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        favouriteSectionRecyclerView.setHasFixedSize(true)
        favouriteSectionRecyclerView.adapter = favouriteAdapter
    }

    private fun setMovieActivity(item: HomeModel.MovieModel){
        startActivity(Intent(activity, MovieItemActivity::class.java)
                .putExtra(AppConstants.MOVIE_EXTRA, item))
        AnimationHelper.enterAnimation(activity)
    }

    override fun onFavouriteSectionClicked(item: HomeModel.MovieModel) {
        setMovieActivity(item)
    }

    override fun onFavouriteClicked(item: FavouriteModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addToFavourite(item: FavouriteModel, holder: BottomSheetAddToFavouriteAdapter.AddToFavouriteHolder) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeFromFavourite(item: FavouriteModel, holder: BottomSheetAddToFavouriteAdapter.AddToFavouriteHolder) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}