package com.eugenetereshkov.testresultant.ui.currencylist

import android.os.Bundle
import com.eugenetereshkov.testresultant.R
import com.eugenetereshkov.testresultant.ui.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_currency_list.*

class CurrencyListFragment : BaseFragment() {

    companion object {
        const val TAG = "currency_list_fragment"

        fun newInstance() = CurrencyListFragment()
    }

    override val idResLayout: Int = R.layout.fragment_currency_list

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toolbar.apply {
            title = getString(R.string.currency_list)
        }
    }
}
