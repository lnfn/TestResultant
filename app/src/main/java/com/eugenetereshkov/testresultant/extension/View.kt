package com.eugenetereshkov.testresultant.extension

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.ViewGroup


fun ViewGroup.inflate(@LayoutRes idResLayout: Int, attachToRoot: Boolean = false) =
        LayoutInflater.from(this.context).inflate(idResLayout, this, false)