package com.eugenetereshkov.testresultant.model.data

import com.eugenetereshkov.testresultant.entity.Currency
import com.eugenetereshkov.testresultant.entity.ServerRepsonse
import io.reactivex.Single
import retrofit2.http.GET

interface ServerApi {

    companion object {
        const val API_URL = "http://phisix-api3.appspot.com"
    }

    @GET("stocks.json")
    fun getCurrencyList(): Single<ServerRepsonse<Currency>>
}
