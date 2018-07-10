package com.eugenetereshkov.testresultant.di.module

import com.eugenetereshkov.testresultant.di.provider.GsonProvider
import com.eugenetereshkov.testresultant.di.provider.OkHttpClientProvider
import com.eugenetereshkov.testresultant.di.provider.ServerApiProvider
import com.eugenetereshkov.testresultant.model.data.ServerApi
import com.eugenetereshkov.testresultant.model.repository.CurrencyListRepository
import com.eugenetereshkov.testresultant.model.repository.ICurrencyListRepository
import com.google.gson.Gson
import okhttp3.OkHttpClient
import toothpick.config.Module

class NetworkModule : Module() {

    init {
        bind(OkHttpClient::class.java).toProvider(OkHttpClientProvider::class.java).providesSingletonInScope()
        bind(Gson::class.java).toProvider(GsonProvider::class.java).providesSingletonInScope()
        bind(ServerApi::class.java).toProvider(ServerApiProvider::class.java).providesSingletonInScope()

        bind(ICurrencyListRepository::class.java).to(CurrencyListRepository::class.java)
    }
}