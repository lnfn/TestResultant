package com.eugenetereshkov.testresultant.presentation.currencylist

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.eugenetereshkov.testresultant.R
import com.eugenetereshkov.testresultant.entity.Currency
import com.eugenetereshkov.testresultant.extension.bindTo
import com.eugenetereshkov.testresultant.model.repository.ICurrencyListRepository
import com.eugenetereshkov.testresultant.model.system.ResourceManager
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@InjectViewState
class CurrencyListPresenter @Inject constructor(
        private val currencyRepository: ICurrencyListRepository,
        private val resourceManager: ResourceManager
) : MvpPresenter<CurrencyListView>() {

    private companion object {
        private const val PERIOD_UPDATE = 15L
    }

    private val disposable = CompositeDisposable()
    private var isFirstLoadingData = true
    private var isRefreshing = false

    override fun onFirstViewAttach() {
        if (isFirstLoadingData) {
            isRefreshing = true
            viewState.showEmptyProgress(true)
        }

        Observable.interval(0L, PERIOD_UPDATE, TimeUnit.SECONDS)
                .flatMapSingle { currencyRepository.getCurrencyList() }
                .subscribe(this::setData, this::handleError)
                .bindTo(disposable)
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }

    fun refreshData() {
        if (!isRefreshing) {
            currencyRepository.getCurrencyList()
                    .doOnSubscribe {
                        isRefreshing = true
                        viewState.showRefreshProgress(true)
                    }
                    .doAfterTerminate { viewState.showRefreshProgress(false) }
                    .subscribe(this::setData, this::handleError)
                    .bindTo(disposable)
        }
    }

    private fun setData(data: List<Currency>) {
        isRefreshing = false

        if (isFirstLoadingData) {
            viewState.showEmptyProgress(false)
            isFirstLoadingData = false
        }
        viewState.setData(data)
    }

    private fun handleError(t: Throwable) {
        viewState.showMessage(resourceManager.getString(R.string.error))
    }
}
