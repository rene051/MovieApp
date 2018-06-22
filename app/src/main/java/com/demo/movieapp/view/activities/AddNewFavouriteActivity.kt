/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.view.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import com.demo.movieapp.R
import com.demo.movieapp.data.models.FavouriteModel
import com.demo.movieapp.data.models.FavouriteSingleton
import com.demo.movieapp.utils.AppConstants.Companion.EDIT_FAVOURITE_FLOW
import com.demo.movieapp.utils.AppConstants.Companion.FAVOURITE_ACTIVITY
import com.demo.movieapp.utils.helpers.AnimationHelper
import com.demo.movieapp.view.BaseActivity
import kotlinx.android.synthetic.main.activity_new_favourite.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Rene on 21.06.18.
 */
class AddNewFavouriteActivity: BaseActivity() {

    private var singleton = FavouriteSingleton.INSTANCE
    private lateinit var favouriteItem: FavouriteModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_favourite)

            supportActionBar?.title = getString(R.string.new_list)

        clickListener()
    }

    private fun clickListener() {
        confirmationButton.setOnClickListener {
            if(intent.getStringExtra(EDIT_FAVOURITE_FLOW) == FAVOURITE_ACTIVITY) {
                favouriteItem = FavouriteModel()
                favouriteItem.id = Random().nextInt(1000).toString()
                favouriteItem.title = favouriteEditText.text.toString()
                favouriteItem.items = ArrayList()
                singleton.favouriteList.add(favouriteItem)
                onBackPressed()
            }
        }

        favouriteEditText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) { }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            @SuppressLint("SetTextI18n")
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                letterCountTextView.text = "${favouriteEditText.length()}/25"
                confirmationButton.isEnabled = favouriteEditText.length() > 0

                if(favouriteEditText.length() > 0) {
                    confirmationButton.background = ContextCompat.getDrawable(applicationContext, R.drawable.button_blue_rounded)
                    confirmationButton.setTextColor(ContextCompat.getColor(applicationContext, R.color.ColorBannerTitle))
                } else {
                    confirmationButton.background = ContextCompat.getDrawable(applicationContext, R.drawable.button_white_rounded)
                    confirmationButton.setTextColor(ContextCompat.getColor(applicationContext, R.color.ColorGreyLight))
                }
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return false
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        AnimationHelper.exitAnimation(this)
    }

}