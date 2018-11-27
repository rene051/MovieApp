package com.demo.movieapp.view.activities

import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import android.content.Intent
import android.widget.Toast
import com.google.android.youtube.player.YouTubePlayerView
import android.os.Bundle
import android.view.Window
import com.demo.movieapp.R
import com.demo.movieapp.utils.AppConstants.Companion.YOUTUBE_API_KEY


class YoutubeDialogActivity: YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {


    private val RECOVERY_REQUEST = 1
    private var youTubeView: YouTubePlayerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.activity_movie_videos)

        youTubeView = findViewById(R.id.youtube_view)
        youTubeView!!.initialize(YOUTUBE_API_KEY, this)
    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
        if (!p2) {
            p1!!.loadVideo(intent.getStringExtra(getString(R.string.id_caps))) // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
        }
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
        if (p1!!.isUserRecoverableError) {
            p1.getErrorDialog(this, RECOVERY_REQUEST).show()
        } else {
            val error = String.format("Error", p1.toString())
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        }    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider()!!.initialize(YOUTUBE_API_KEY, this)
        }
    }

    private fun getYouTubePlayerProvider(): YouTubePlayerView? {
        return youTubeView
    }

}