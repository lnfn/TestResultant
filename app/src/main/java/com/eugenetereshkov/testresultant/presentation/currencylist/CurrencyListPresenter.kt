package com.eugenetereshkov.testresultant.presentation.currencylist

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.eugenetereshkov.testresultant.R
import com.eugenetereshkov.testresultant.entity.Currency
import com.eugenetereshkov.testresultant.extension.bindTo
import com.eugenetereshkov.testresultant.model.repository.ICurrencyListRepository
import com.eugenetereshkov.testresultant.model.system.ResourceManager
import com.eugenetereshkov.testresultant.model.system.SchedulersProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@InjectViewState
class CurrencyListPresenter @Inject constructor(
        private val currencyRepository: ICurrencyListRepository,
        private val resourceManager: ResourceManager,
        private val schedulersProvider: SchedulersProvider
) : MvpPresenter<CurrencyListView>() {

    private companion object {
        private const val PERIOD_UPDATE = 15L
    }

    private val disposable = CompositeDisposable()
    private var isFirstLoadingData = true

    override fun onFirstViewAttach() {
        if (isFirstLoadingData) viewState.showEmptyProgress(true)

        Observable.interval(0L, PERIOD_UPDATE, TimeUnit.SECONDS)
                .flatMapSingle { currencyRepository.getCurrencyList() }
                .subscribe(this::setData, this::handleError)
                .bindTo(disposable)
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }

    fun refreshData() = currencyRepository.getCurrencyList()
            .subscribe(this::setData, this::handleError)
            .bindTo(disposable)

    private fun setData(data: List<Currency>) {
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
