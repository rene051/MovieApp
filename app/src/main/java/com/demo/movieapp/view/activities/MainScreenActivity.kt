/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.view.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.MenuItem
import com.demo.movieapp.R
import com.demo.movieapp.common.enums.FragmentTag
import com.demo.movieapp.di.component.DaggerMainScreenComponent
import com.demo.movieapp.di.module.MainScreenModule
import com.demo.movieapp.view.BaseActivity
import com.demo.movieapp.view.fragments.*
import com.demo.movieapp.view.view.MainScreenView
import kotlinx.android.synthetic.main.activity_main_screen.*

/**
 * Created by Rene on 21.06.18.
 */
class MainScreenActivity : BaseActivity(), MainScreenView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        DaggerMainScreenComponent.builder()
                .appComponent(this.getApplicationComponent())
                .mainScreenModule(MainScreenModule(this))
                .build().inject(this)

        setBottomNavigationViews()
        clickListeners()
        homeClicked()
    }

    private fun setBottomNavigationViews() {
        menuBottomNavigation.enableAnimation(true)
        menuBottomNavigation.enableShiftingMode(true)
        menuBottomNavigation.enableItemShiftingMode(true)
        menuBottomNavigation.setTextVisibility(true)
    }

    private fun clickListeners() {

        menuBottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home_news -> homeClicked()
                R.id.action_favourite_movie -> favouriteClicked()
                R.id.action_search_movie -> searchClicked()
            }
            true
        }
    }

    private fun homeClicked() {
        setFragment(HomeFragment.newInstance(), FragmentTag.HomeFragment.getTag())
    }

    private fun favouriteClicked() {
        setFragment(FavouriteFragment.newInstance(), FragmentTag.FavouriteFragment.getTag())
    }

    private fun searchClicked() {
        setFragment(SearchFragment.newInstance(), FragmentTag.SearchFragment.getTag())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                return false
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFragment(currentFragment: Fragment, fragmentTag: String) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentMainScreenContainer, currentFragment, fragmentTag)
                .commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.clear()
    }

    override fun addLoadingFooter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeLoadingFooter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}