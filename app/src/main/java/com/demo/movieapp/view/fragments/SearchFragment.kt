package com.demo.movieapp.view.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.cinnamon.utils.network.CinnamonNetwork
import com.demo.movieapp.R
import com.demo.movieapp.data.models.ErrorResponseModel
import com.demo.movieapp.data.models.FavouriteModel
import com.demo.movieapp.data.models.HomeModel
import com.demo.movieapp.data.models.VideoModel
import com.demo.movieapp.di.component.DaggerHomeComponent
import com.demo.movieapp.di.module.HomeModule
import com.demo.movieapp.domain.listeners.FavouriteAdapterClickListener
import com.demo.movieapp.presenter.HomePresenter
import com.demo.movieapp.utils.AppConstants
import com.demo.movieapp.utils.helpers.AnimationHelper
import com.demo.movieapp.utils.helpers.ErrorCodeHelper
import com.demo.movieapp.view.BaseFragment
import com.demo.movieapp.view.activities.MovieItemActivity
import com.demo.movieapp.view.adapters.BottomSheetAddToFavouriteAdapter
import com.demo.movieapp.view.adapters.FavouriteSectionAdapter
import com.demo.movieapp.view.view.HomeView
import kotlinx.android.synthetic.main.fragment_search.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager


/**
 * Created by Rene on 22.06.18..
 */
class SearchFragment : BaseFragment(), HomeView, FavouriteAdapterClickListener {


    @Inject
    lateinit var homePresenter: HomePresenter
    private var timer: Timer? = null
    private lateinit var searchAdapter: FavouriteSectionAdapter
    private var movieList = ArrayList<HomeModel.MovieModel>()

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
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

        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        clickListener()
    }

    override fun onResume() {
        super.onResume()

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.search_movies)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        requireActivity().invalidateOptionsMenu()
    }

    private fun clickListener() {
        searchEditText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {

                if (CinnamonNetwork.isNetworkAvailable(requireContext())) {
                    timer = Timer()
                    timer!!.schedule(object : TimerTask() {
                        override fun run() {
                            if (searchEditText.length() != 0) {
                                homePresenter.searchMovies(searchEditText.text.toString())
                            }
                        }
                    }, 600)
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if (timer != null) {
                    timer!!.cancel()
                }
            }
        })

        deleteImageView.setOnClickListener {
            searchEditText.setText("")
        }
    }

    override fun searchMovieSuccess(item: HomeModel) {
        movieList.clear()
        movieList.addAll(item.result!!)
        searchAdapter.notifyDataSetChanged()
    }

    override fun searchMovieFailed(e: ErrorResponseModel) {

        ErrorCodeHelper.handleResponseError(requireActivity(), e)
    }

    private fun setAdapter() {
        searchAdapter = FavouriteSectionAdapter(requireActivity(), movieList, this)

        moviesRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        moviesRecyclerView.adapter = searchAdapter

        moviesRecyclerView.setOnTouchListener { _, _ ->
            val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view!!.windowToken, 0)
        }
    }

    private fun setMovieActivity(item: HomeModel.MovieModel) {
        startActivity(Intent(activity, MovieItemActivity::class.java)
                .putExtra(AppConstants.MOVIE_EXTRA, item))
        AnimationHelper.enterAnimation(activity)
    }

    override fun onFavouriteSectionClicked(item: HomeModel.MovieModel) {
        setMovieActivity(item)
    }

    override fun addToFavourite(item: FavouriteModel, holder: BottomSheetAddToFavouriteAdapter.AddToFavouriteHolder) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeFromFavourite(item: FavouriteModel, holder: BottomSheetAddToFavouriteAdapter.AddToFavouriteHolder) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFavouriteClicked(item: FavouriteModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun movieFetchSuccess(item: HomeModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun movieFetchFailed(e: ErrorResponseModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun moviePaginationSuccess(item: HomeModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addLoadingFooter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeLoadingFooter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun videoFetchedSuccess(item: VideoModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun videoFecthFailed(e: ErrorResponseModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}