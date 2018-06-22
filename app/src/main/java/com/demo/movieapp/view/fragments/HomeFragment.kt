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
import android.support.v7.widget.RecyclerView
import android.view.*
import com.cinnamon.utils.network.CinnamonNetwork
import com.demo.movieapp.R
import com.demo.movieapp.data.models.ErrorResponseModel
import com.demo.movieapp.data.models.HomeModel
import com.demo.movieapp.di.component.DaggerHomeComponent
import com.demo.movieapp.di.module.HomeModule
import com.demo.movieapp.domain.listeners.HomeAdapterClickListener
import com.demo.movieapp.presenter.HomePresenter
import com.demo.movieapp.utils.AppConstants.Companion.MOVIE_EXTRA
import com.demo.movieapp.utils.helpers.AnimationHelper
import com.demo.movieapp.utils.helpers.ErrorCodeHelper
import com.demo.movieapp.view.BaseFragment
import com.demo.movieapp.view.activities.MovieItemActivity
import com.demo.movieapp.view.adapters.HomeMainAdapter
import com.demo.movieapp.view.view.HomeView
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by Rene on 21.06.18.
 */
class HomeFragment : BaseFragment(), HomeView, HomeAdapterClickListener {

    @Inject
    lateinit var homePresenter: HomePresenter

    private lateinit var homeAdapter: HomeMainAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var homeModel: HomeModel
    private var loading = false
    private var noInternet = true

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerHomeComponent.builder()
                .appComponent(this.getApplicationComponent())
                .homeModule(HomeModule(this))
                .build().inject(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getPopularMovie()
    }

    override fun onResume() {
        super.onResume()

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.most_popular)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        requireActivity().invalidateOptionsMenu()
    }


    private fun getPopularMovie() {
        if (CinnamonNetwork.isNetworkAvailable(requireContext())) {
            homeProgressBar.visibility = View.VISIBLE
            homePresenter.getPopularMovie(1)
        }
    }

    override fun onItemClicked(item: HomeModel.MovieModel) {
        setMovieActivity(item)
    }

    override fun movieFetchSuccess(item: HomeModel) {
        if(isAdded) {
            homeProgressBar.visibility = View.INVISIBLE
            homeModel = item
            setAdapter()
        }
    }

    override fun movieFetchFailed(e: ErrorResponseModel) {
        homeProgressBar.visibility = View.INVISIBLE
        ErrorCodeHelper.handleResponseError(requireActivity(), e)
    }

    override fun moviePaginationSuccess(item: HomeModel) {
        loading = false
        homeModel.page = item.page
        homeModel.result?.addAll(item.result!!)
        homeAdapter.notifyDataSetChanged()
    }

    override fun addLoadingFooter() {
        homeAdapter.addLoadingFooter()
    }

    override fun removeLoadingFooter() {
        homeAdapter.removeLoadingFooter()
    }

    private fun setMovieActivity(item: HomeModel.MovieModel){
        startActivity(Intent(activity, MovieItemActivity::class.java)
                .putExtra(MOVIE_EXTRA, item))
        AnimationHelper.enterAnimation(activity)
    }

    private fun setAdapter() {
        homeAdapter = HomeMainAdapter(requireActivity(), homeModel.result!!, this)

        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        homeRecyclerView.layoutManager = layoutManager
        homeRecyclerView.setHasFixedSize(true)
        homeRecyclerView.adapter = homeAdapter
        pagination()
    }

    private fun pagination() {

        homeRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val myTotalCount = homeModel.result!!.size - 2

                if (dy > 0) { //dy scrolling down
                    if ((firstVisibleItemPosition >= myTotalCount) && firstVisibleItemPosition > 0
                            && myTotalCount > 0 && (myTotalCount + 2) <= totalItemCount) {
                        if(!loading) {
                            if (CinnamonNetwork.isNetworkAvailable(context)) {
                                if (isAdded) {
                                    homePresenter.getPopularMovie(homeModel.page!! + 1)
                                    addLoadingFooter()
                                    loading = true
                                    noInternet = true
                                }
                            }
                        }
                    }
                }
            }
        })
    }

    override fun searchMovieSuccess(item: HomeModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun searchMovieFailed(e: ErrorResponseModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}