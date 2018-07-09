package com.eugenetereshkov.testresultant.ui.main

import android.os.Bundle
import com.eugenetereshkov.testresultant.R
import com.eugenetereshkov.testresultant.ui.common.BaseActivity
import com.eugenetereshkov.testresultant.ui.currencylist.CurrencyListFragment

class MainActivity : BaseActivity() {

    override val idResLayout: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) initMainScreen()
    }

    private fun initMainScreen() {
        supportFragmentManager.beginTransaction()
                .add(R.id.container, CurrencyListFragment.newInstance(), CurrencyListFragment.TAG)
                .commitNow()
    }
}
