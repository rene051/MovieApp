package com.demo.movieapp.view.activities

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.widget.ImageView
import android.widget.Toast
import com.cinnamon.utils.date.CinnamonDate
import com.demo.movieapp.R
import com.demo.movieapp.data.api.APIConstants.Companion.IMAGE_BASE_URL
import com.demo.movieapp.data.models.ErrorResponseModel
import com.demo.movieapp.data.models.HomeModel
import com.demo.movieapp.data.models.VideoModel
import com.demo.movieapp.di.component.DaggerHomeComponent
import com.demo.movieapp.di.module.HomeModule
import com.demo.movieapp.presenter.HomePresenter
import com.demo.movieapp.utils.AppConstants.Companion.MOVIE_EXTRA
import com.demo.movieapp.utils.helpers.AnimationHelper
import com.demo.movieapp.utils.helpers.ErrorCodeHelper
import com.demo.movieapp.view.BaseActivity
import com.demo.movieapp.view.fragments.BottomSheetAddFavouriteFragment
import com.demo.movieapp.view.view.HomeView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.item_toolbar.*
import javax.inject.Inject

/**
 * Created by Rene on 21.06.18.
 */
class MovieItemActivity : BaseActivity(), HomeView {

    @Inject
    lateinit var homePresenter: HomePresenter

    private var videoFetched: Boolean = false
    private lateinit var sharedContentText: String
    private lateinit var youtubeKey: String
    private lateinit var shareIntent: Intent
    private lateinit var imagePreviewDialog: Dialog
    private lateinit var imagePreviewImageView: ImageView
    private lateinit var movieItem: HomeModel.MovieModel
    private lateinit var bottomSheetFragment: BottomSheetAddFavouriteFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerHomeComponent.builder()
                .appComponent(this.getApplicationComponent())
                .homeModule(HomeModule(this))
                .build().inject(this)

        setContentView(R.layout.fragment_movie)

        movieItem = intent.getSerializableExtra(MOVIE_EXTRA) as HomeModel.MovieModel

        setSupportActionBar(mainToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.get_popular)

        mainToolbar.title = movieItem.title
        homePresenter.getMovieVideo(movieItem.id)
        setViews()
        onClickListeners()
    }

    private fun setViews() {
        imagePreviewDialog = Dialog(this)
        imagePreviewDialog.window.requestFeature(Window.FEATURE_NO_TITLE)
        imagePreviewDialog.setContentView(layoutInflater.inflate(R.layout.dialog_image_preview, null))

        imagePreviewImageView = imagePreviewDialog.findViewById(R.id.imagePreviewImageView)

        Picasso.get().load(IMAGE_BASE_URL + movieItem.posterPath).fit().centerCrop().into(mainMovieImageView)

        if(movieItem.releaseDate != null && movieItem.releaseDate!!.isNotEmpty())  {
            releaseDate.text = CinnamonDate.convertDate(movieItem.releaseDate, "YYYY-MM-dd", "dd MMM YYYY")
        }

        ratingTextView.text = movieItem.voteAverage.toString()
        titleTextView.text = movieItem.title
        descriptionTextView.text = movieItem.overview
    }

    private fun onClickListeners() {
        mainMovieImageView.setOnClickListener {
            Picasso.get().load(IMAGE_BASE_URL + movieItem.posterPath).fit().centerCrop().into(imagePreviewImageView)
            imagePreviewDialog.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.movie_videos, menu)
        menuInflater.inflate(R.menu.share_movie, menu)
        menuInflater.inflate(R.menu.favourite_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.action_show_videos -> {
                if(videoFetched) {
                    startActivity(Intent(this, YoutubeDialogActivity::class.java)
                            .putExtra(getString(R.string.id_caps), youtubeKey))
                    AnimationHelper.enterAnimation(this)
                } else {
                    Toast.makeText(this, getString(R.string.video_is_not_fetched), Toast.LENGTH_SHORT).show()
                }
            }
            R.id.action_favourite_movie -> {
                bottomSheetFragment = BottomSheetAddFavouriteFragment.newInstance(movieItem)
                bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
            }
            R.id.action_share_movie -> {
                shareIntent = Intent(Intent.ACTION_SEND)

                shareIntent.type = "*/*"

                sharedContentText = if(::youtubeKey.isInitialized) {
                    movieItem.title + "\n\n" + movieItem.overview + "\n\n" + getString(R.string.youtube_base_link) + youtubeKey
                } else {
                    movieItem.title + "\n\n" + movieItem.overview

                }

                shareIntent.putExtra(Intent.EXTRA_TEXT, sharedContentText)
                startActivity(Intent.createChooser(shareIntent, getString(R.string.share_via)))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        AnimationHelper.exitAnimation(this)
        finish()
    }

    override fun videoFetchedSuccess(item: VideoModel) {
        if(item.result?.size != null && item.result!!.size > 0) {
            youtubeKey = item.result!![0].key!!
            videoFetched = true
        }
    }

    override fun videoFecthFailed(e: ErrorResponseModel) {
        ErrorCodeHelper.handleResponseError(this, e)
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

    override fun searchMovieSuccess(item: HomeModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun searchMovieFailed(e: ErrorResponseModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addLoadingFooter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeLoadingFooter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}