package com.demo.movieapp.view.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.cinnamon.utils.date.CinnamonDate
import com.demo.movieapp.R
import com.demo.movieapp.data.api.APIConstants.Companion.IMAGE_BASE_URL
import com.demo.movieapp.data.models.HomeModel
import com.demo.movieapp.utils.AppConstants.Companion.MOVIE_EXTRA
import com.demo.movieapp.utils.helpers.AnimationHelper
import com.demo.movieapp.view.BaseActivity
import com.demo.movieapp.view.fragments.BottomSheetAddFavouriteFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * Created by Rene on 21.06.18.
 */
class MovieItemActivity : BaseActivity() {

    private lateinit var movieItem: HomeModel.MovieModel
    private lateinit var bottomSheetFragment: BottomSheetAddFavouriteFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_movie)

        movieItem = intent.getSerializableExtra(MOVIE_EXTRA) as HomeModel.MovieModel

        supportActionBar!!.title = movieItem.title
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setViews()
    }

    private fun setViews() {
        Picasso.get().load(IMAGE_BASE_URL + movieItem.posterPath).fit().centerCrop().into(mainMovieImageView)
        if(movieItem.releaseDate != null && movieItem.releaseDate!!.isNotEmpty())  {
            releaseDate.text = CinnamonDate.convertDate(movieItem.releaseDate, "YYYY-MM-dd", "dd MMM YYYY")
        }
        ratingTextView.text = movieItem.voteAverage.toString()
        titleTextView.text = movieItem.title
        descriptionTextView.text = movieItem.overview
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favourite_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.action_favourite_movie -> {
                bottomSheetFragment = BottomSheetAddFavouriteFragment.newInstance(movieItem)
                bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        AnimationHelper.exitAnimation(this)
        finish()
    }
}