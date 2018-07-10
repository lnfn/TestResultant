package com.eugenetereshkov.testresultant.presentation.currencylist

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.eugenetereshkov.testresultant.entity.Currency


@StateStrategyType(AddToEndSingleStrategy::class)
interface CurrencyListView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun showMessage(message: String)

    fun setData(data: List<Currency>)
    fun showEmptyProgress(show: Boolean)
}