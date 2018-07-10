package com.eugenetereshkov.testresultant.di.provider

import com.eugenetereshkov.testresultant.model.data.ServerApi
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Provider


class ServerApiProvider @Inject constructor(
        private val okHttpClient: OkHttpClient,
        private val gson: Gson
) : Provider<ServerApi> {

    override fun get() =
            Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .baseUrl(ServerApi.API_URL)
                    .build()
                    .create(ServerApi::class.java)
}