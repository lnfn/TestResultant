package com.eugenetereshkov.testresultant.ui.currencylist

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.eugenetereshkov.testresultant.DI
import com.eugenetereshkov.testresultant.R
import com.eugenetereshkov.testresultant.entity.Currency
import com.eugenetereshkov.testresultant.presentation.currencylist.CurrencyListPresenter
import com.eugenetereshkov.testresultant.presentation.currencylist.CurrencyListView
import com.eugenetereshkov.testresultant.ui.common.BaseFragment
import com.eugenetereshkov.testresultant.ui.common.SimpleDividerDecorator
import kotlinx.android.synthetic.main.fragment_currency_list.*
import toothpick.Toothpick

class CurrencyListFragment : BaseFragment(), CurrencyListView {

    companion object {
        const val TAG = "currency_list_fragment"

        fun newInstance() = CurrencyListFragment()
    }

    override val idResLayout: Int = R.layout.fragment_currency_list

    @InjectPresenter
    lateinit var presenter: CurrencyListPresenter

    @ProvidePresenter
    fun providePresenter() =
            Toothpick
                    .openScopes(DI.NETWORK_SCOPE, DI.CURRENCY_LIST_FRAGMENT_SCOPE)
                    .getInstance(CurrencyListPresenter::class.java).also {
                        Toothpick.closeScope(DI.CURRENCY_LIST_FRAGMENT_SCOPE)
                    }

    private val adapter by lazy { CurrencyListAdapter() }
    private val menuItemClickListListener by lazy {
        Toolbar.OnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.action_refresh -> {
                    presenter.refreshData()
                    return@OnMenuItemClickListener true
                }
            }
            false
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toolbar.apply {
            title = getString(R.string.currency_list)
            inflateMenu(R.menu.menu_main)
            setOnMenuItemClickListener(menuItemClickListListener)
        }

        swipeRefresh.setOnRefreshListener { presenter.refreshData() }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(SimpleDividerDecorator(context))
            setHasFixedSize(true)
            adapter = this@CurrencyListFragment.adapter
        }
    }

    override fun setData(data: List<Currency>) {
        adapter.submitList(data)
        context?.toast("Updated")
    }

    override fun showEmptyProgress(show: Boolean) {
        recyclerView.isGone = show
        progressBar.isVisible = show
    }

    override fun showRefreshProgress(show: Boolean) {
        swipeRefresh.isRefreshing = show
    }

    override fun showMessage(message: String) {
        context?.toast(message)
    }
}
