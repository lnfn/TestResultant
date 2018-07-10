package com.eugenetereshkov.testresultant.di.provider

import com.eugenetereshkov.testresultant.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Provider


class OkHttpClientProvider @Inject constructor() : Provider<OkHttpClient> {

    override fun get() = OkHttpClient.Builder().apply {
        connectTimeout(30, TimeUnit.SECONDS)
        readTimeout(30, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            addNetworkInterceptor(
                    HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
            )
        }
    }.build()
}