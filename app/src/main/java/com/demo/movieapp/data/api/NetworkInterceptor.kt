
/*
 * Copyright (c) Rene Škuljević in Zagreb 2018
 *
 * All rights reserved.
 */

package com.demo.movieapp.data.api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by Rene on 21.06.18.
 */

class NetworkInterceptor(private val osVersionCode: Int, private val appVersionName: String) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val builder = request.newBuilder()
        builder.addHeader(HEADER_PLATFORM, PLATFORM_ANDROID)
                .addHeader(HEADER_OS_VERSION, osVersionCode.toString())
                .addHeader(HEADER_APP_VERSION, appVersionName)

        return chain.proceed(builder.build())
    }

    companion object {

        private const val HEADER_PLATFORM = "X-Mobile-OS"

        private const val PLATFORM_ANDROID = "Android"

        private const val HEADER_OS_VERSION = "X-Mobile-OS-Version"

        private const val HEADER_APP_VERSION = "X-Mobile-App-Build"
    }
}