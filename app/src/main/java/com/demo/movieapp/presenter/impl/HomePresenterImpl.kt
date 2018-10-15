/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.presenter.impl

import android.content.Context
import com.demo.movieapp.data.api.APIConstants.Companion.apiKey
import com.demo.movieapp.data.api.NetworkApi
import com.demo.movieapp.data.models.HomeModel
import com.demo.movieapp.data.models.VideoModel
import com.demo.movieapp.di.module.ThreadModule
import com.demo.movieapp.domain.interactors.HomeInteractor
import com.demo.movieapp.presenter.HomePresenter
import com.demo.movieapp.utils.helpers.ErrorCodeHelper
import com.demo.movieapp.view.view.HomeView
import io.reactivex.Scheduler
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Rene on 22.06.18.
 */
class HomePresenterImpl
@Inject
constructor(private val context: Context, private val profileView: HomeView,
            private val homeInteractor: HomeInteractor) : HomePresenter {


    @Inject
    @field:Named(ThreadModule.SUBSCRIBE_SCHEDULER)
    lateinit var subscribeScheduler: Scheduler

    @Inject
    @field:Named(ThreadModule.OBSERVE_SCHEDULER)
    lateinit var observeScheduler: Scheduler

    @Inject
    lateinit var networkApi: NetworkApi


    override fun getPopularMovie(page: Int) {
        networkApi.getPopularMovie(apiKey, page)
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .subscribe(object : SingleObserver<HomeModel> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onSuccess(t: HomeModel) {
                        if(t.page == 1) {
                            profileView.movieFetchSuccess(t)
                        } else {
                            profileView.removeLoadingFooter()
                            profileView.moviePaginationSuccess(t)
                        }
                    }

                    override fun onError(e: Throwable) {
                        profileView.movieFetchFailed(ErrorCodeHelper.castErrorResponse(e))
                    }

                })
    }

    override fun getNowPlayingMovie(page: Int) {
        networkApi.getNowPlayingMovie(apiKey, page)
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .subscribe(object : SingleObserver<HomeModel> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onSuccess(t: HomeModel) {
                        if(t.page == 1) {
                            profileView.movieFetchSuccess(t)
                        } else {
                            profileView.removeLoadingFooter()
                            profileView.moviePaginationSuccess(t)
                        }
                    }

                    override fun onError(e: Throwable) {
                        profileView.movieFetchFailed(ErrorCodeHelper.castErrorResponse(e))
                    }

                })    }

    override fun getTopRatedMovie(page: Int) {
        networkApi.getTopRatedMovie(apiKey, page)
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .subscribe(object : SingleObserver<HomeModel> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onSuccess(t: HomeModel) {
                        if(t.page == 1) {
                            profileView.movieFetchSuccess(t)
                        } else {
                            profileView.removeLoadingFooter()
                            profileView.moviePaginationSuccess(t)
                        }
                    }

                    override fun onError(e: Throwable) {
                        profileView.movieFetchFailed(ErrorCodeHelper.castErrorResponse(e))
                    }

                })    }

    override fun getUpcomingMovie(page: Int) {
        networkApi.getUpcomingMovie(apiKey, page)
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .subscribe(object : SingleObserver<HomeModel> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onSuccess(t: HomeModel) {
                        if(t.page == 1) {
                            profileView.movieFetchSuccess(t)
                        } else {
                            profileView.removeLoadingFooter()
                            profileView.moviePaginationSuccess(t)
                        }
                    }

                    override fun onError(e: Throwable) {
                        profileView.movieFetchFailed(ErrorCodeHelper.castErrorResponse(e))
                    }

                })
    }

    override fun searchMovies(search: String) {
        networkApi.searchMovies(apiKey, search)
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .subscribe(object : SingleObserver<HomeModel> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onSuccess(t: HomeModel) {
                        profileView.searchMovieSuccess(t)
                    }

                    override fun onError(e: Throwable) {
                        profileView.searchMovieFailed(ErrorCodeHelper.castErrorResponse(e))
                    }

                })
    }

    override fun getMovieVideo(movieId: Int?) {
        networkApi.getMovieVideos(movieId.toString(), apiKey )
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .subscribe(object : SingleObserver<VideoModel> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onSuccess(t: VideoModel) {
                        profileView.videoFetchedSuccess(t)
                    }

                    override fun onError(e: Throwable) {
                        profileView.videoFecthFailed(ErrorCodeHelper.castErrorResponse(e))
                    }

                })
    }
}