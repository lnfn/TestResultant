package com.eugenetereshkov.testresultant.ui.launch

import android.content.Intent
import android.os.Bundle
import com.eugenetereshkov.testresultant.R
import com.eugenetereshkov.testresultant.ui.common.BaseActivity
import com.eugenetereshkov.testresultant.ui.main.MainActivity


class LaunchActivity : BaseActivity() {
    override val idResLayout: Int = R.layout.activity_launch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
