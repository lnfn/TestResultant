package com.eugenetereshkov.testresultant.model.repository

import com.eugenetereshkov.testresultant.entity.Currency
import com.eugenetereshkov.testresultant.model.data.ServerApi
import com.eugenetereshkov.testresultant.model.system.SchedulersProvider
import io.reactivex.Single
import javax.inject.Inject


interface ICurrencyListRepository {
    fun getCurrencyList(): Single<List<Currency>>
}

class CurrencyListRepository @Inject constructor(
        private val serverApi: ServerApi,
        private val schedulersProvider: SchedulersProvider
) : ICurrencyListRepository {

    override fun getCurrencyList(): Single<List<Currency>> =
            serverApi.getCurrencyList()
                    .map { it.stock }
                    .subscribeOn(schedulersProvider.io())
                    .observeOn(schedulersProvider.ui())
}