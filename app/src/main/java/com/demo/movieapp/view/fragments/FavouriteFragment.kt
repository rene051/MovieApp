/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.view.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.Toast
import com.demo.movieapp.R
import com.demo.movieapp.common.enums.FragmentTag
import com.demo.movieapp.data.models.FavouriteModel
import com.demo.movieapp.data.models.FavouriteSingleton
import com.demo.movieapp.data.models.HomeModel
import com.demo.movieapp.domain.listeners.FavouriteAdapterClickListener
import com.demo.movieapp.utils.AppConstants.Companion.EDIT_FAVOURITE_FLOW
import com.demo.movieapp.utils.AppConstants.Companion.FAVOURITE_ACTIVITY
import com.demo.movieapp.utils.helpers.AnimationHelper
import com.demo.movieapp.view.BaseFragment
import com.demo.movieapp.view.activities.AddNewFavouriteActivity
import com.demo.movieapp.view.adapters.BottomSheetAddToFavouriteAdapter
import com.demo.movieapp.view.adapters.FavouriteAdapter
import kotlinx.android.synthetic.main.fragment_favourite.*

/**
 * Created by Rene on 21.06.18.
 */
class FavouriteFragment : BaseFragment(), FavouriteAdapterClickListener {

    private var addListItem: MenuItem? = null
    private lateinit var favouriteAdapter: FavouriteAdapter
    private var singleton = FavouriteSingleton.INSTANCE

    companion object {
        fun newInstance(): FavouriteFragment {
            return FavouriteFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar!!.title = "Your favourites"
        (activity as AppCompatActivity).supportActionBar?.elevation = 8F

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(false)

        setAdapter()
    }

    override fun onResume() {
        super.onResume()

        favouriteAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(R.menu.add_lists, menu)

        addListItem = menu?.findItem(R.id.add_list_action)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressed()
                return true
            }
            R.id.add_list_action -> {
                startActivity(Intent(requireContext(), AddNewFavouriteActivity::class.java)
                        .putExtra(EDIT_FAVOURITE_FLOW, FAVOURITE_ACTIVITY))
                AnimationHelper.enterAnimation(requireActivity())
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun setAdapter() {
        favouriteAdapter = FavouriteAdapter(requireActivity(), singleton.favouriteList, this)

        favouriteRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        favouriteRecyclerView.setHasFixedSize(true)
        favouriteRecyclerView.adapter = favouriteAdapter
    }

    private fun setFragment(currentFragment: Fragment, fragmentTag: String) {
        fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragmentMainScreenContainer, currentFragment, fragmentTag)
                ?.addToBackStack(fragmentTag)
                ?.commit()
    }

    override fun onFavouriteClicked(item: FavouriteModel) {
        if(item.items?.size!! > 0) {
            setFragment(FavouriteSectionFragment.newInstance(item), FragmentTag.FavouriteSectionFragment.getTag())
        } else {
            Toast.makeText(requireContext(), "List is empty!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onFavouriteSectionClicked(item: HomeModel.MovieModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addToFavourite(item: FavouriteModel, holder: BottomSheetAddToFavouriteAdapter.AddToFavouriteHolder) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeFromFavourite(item: FavouriteModel, holder: BottomSheetAddToFavouriteAdapter.AddToFavouriteHolder) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}