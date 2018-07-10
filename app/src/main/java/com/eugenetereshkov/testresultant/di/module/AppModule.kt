package com.eugenetereshkov.testresultant.di.module

import android.content.Context
import com.eugenetereshkov.testresultant.model.system.AppSchedulers
import com.eugenetereshkov.testresultant.model.system.ResourceManager
import com.eugenetereshkov.testresultant.model.system.SchedulersProvider
import toothpick.config.Module

class AppModule(context: Context) : Module() {

    init {
        bind(Context::class.java).toInstance(context)
        bind(ResourceManager::class.java).singletonInScope()
        bind(SchedulersProvider::class.java).toInstance(AppSchedulers())
    }
}
