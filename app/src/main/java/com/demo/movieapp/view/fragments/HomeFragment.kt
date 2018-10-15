/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.view.fragments


import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.RelativeLayout
import com.cinnamon.utils.network.CinnamonNetwork
import com.demo.movieapp.R
import com.demo.movieapp.data.models.ErrorResponseModel
import com.demo.movieapp.data.models.HomeModel
import com.demo.movieapp.data.models.VideoModel
import com.demo.movieapp.di.component.DaggerHomeComponent
import com.demo.movieapp.di.module.HomeModule
import com.demo.movieapp.domain.listeners.HomeAdapterClickListener
import com.demo.movieapp.presenter.HomePresenter
import com.demo.movieapp.utils.AppConstants.Companion.MOVIE_EXTRA
import com.demo.movieapp.utils.AppConstants.Companion.NOW_PLAYING
import com.demo.movieapp.utils.AppConstants.Companion.POPULAR
import com.demo.movieapp.utils.AppConstants.Companion.TOP_RATED
import com.demo.movieapp.utils.AppConstants.Companion.UPCOMING
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

    private var addListItem: MenuItem? = null
    private lateinit var homeAdapter: HomeMainAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var homeModel: HomeModel
    private var chosenFilter: String = POPULAR
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

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.get_popular)
        getPopularMovie()
    }

    override fun onResume() {
        super.onResume()

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        requireActivity().invalidateOptionsMenu()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(R.menu.filter_home_list, menu)

        addListItem = menu?.findItem(R.id.add_filter_action)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.add_filter_action -> {
                openDialogFilter()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openDialogFilter() {

        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.filter_home_list_dialog)

        val nowPlaying = dialog.findViewById<RelativeLayout>(R.id.getNowPlayingRelativeLayout)
        val popular = dialog.findViewById<RelativeLayout>(R.id.getPopularRelativeLayout)
        val topRated = dialog.findViewById<RelativeLayout>(R.id.getTopRatedRelativeLayout)
        val upcoming = dialog.findViewById<RelativeLayout>(R.id.getUpcomingRelativeLayout)

        nowPlaying.setOnClickListener {
            chosenFilter = NOW_PLAYING
            dialog.dismiss()
            getMovies()
        }

        popular.setOnClickListener {
            chosenFilter = POPULAR
            dialog.dismiss()
            getMovies()
        }

        topRated.setOnClickListener {
            chosenFilter = TOP_RATED
            dialog.dismiss()
            getMovies()
        }

        upcoming.setOnClickListener {
            chosenFilter = UPCOMING
            dialog.dismiss()
            getMovies()
        }


        dialog.show()
    }

    private fun getMovies() {

        when (chosenFilter) {
            POPULAR -> {
                (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.get_popular)
                getPopularMovie()
            }
            NOW_PLAYING -> {
                (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.get_now_playing)
                getNowPlayingMovie()
            }
            TOP_RATED -> {
                (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.get_top_rated)
                getTopRatedMovie()
            }
            UPCOMING -> {
                (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.get_upcoming)
                getUpcomingMovie()
            }
        }

    }

    private fun getNextPageMovies() {
        when (chosenFilter) {
            POPULAR -> {
                homePresenter.getPopularMovie(homeModel.page!! + 1)
            }
            NOW_PLAYING -> {
                homePresenter.getNowPlayingMovie(homeModel.page!! + 1)
            }
            TOP_RATED -> {
                homePresenter.getTopRatedMovie(homeModel.page!! + 1)
            }
            UPCOMING -> {
                homePresenter.getUpcomingMovie(homeModel.page!! + 1)

            }
        }
    }

    private fun getPopularMovie() {
        if (CinnamonNetwork.isNetworkAvailable(requireContext())) {
            homeProgressBar.visibility = View.VISIBLE
            homePresenter.getPopularMovie(1)
        }
    }

    private fun getNowPlayingMovie() {
        if (CinnamonNetwork.isNetworkAvailable(requireContext())) {
            homeProgressBar.visibility = View.VISIBLE
            homePresenter.getNowPlayingMovie(1)
        }
    }

    private fun getTopRatedMovie() {
        if (CinnamonNetwork.isNetworkAvailable(requireContext())) {
            homeProgressBar.visibility = View.VISIBLE
            homePresenter.getTopRatedMovie(1)
        }
    }

    private fun getUpcomingMovie() {
        if (CinnamonNetwork.isNetworkAvailable(requireContext())) {
            homeProgressBar.visibility = View.VISIBLE
            homePresenter.getUpcomingMovie(1)
        }
    }


    override fun onItemClicked(item: HomeModel.MovieModel) {
        setMovieActivity(item)
    }

    override fun movieFetchSuccess(item: HomeModel) {
        if (isAdded) {
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

    private fun setMovieActivity(item: HomeModel.MovieModel) {
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
                        if (!loading) {
                            if (CinnamonNetwork.isNetworkAvailable(context)) {
                                if (isAdded) {
                                    getNextPageMovies()
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

    override fun videoFetchedSuccess(item: VideoModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun videoFecthFailed(e: ErrorResponseModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}