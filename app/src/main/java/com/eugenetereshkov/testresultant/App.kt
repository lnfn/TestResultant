package com.eugenetereshkov.testresultant

import android.app.Application
import com.eugenetereshkov.testresultant.di.module.AppModule
import com.eugenetereshkov.testresultant.di.module.NetworkModule
import timber.log.Timber
import toothpick.Toothpick
import toothpick.configuration.Configuration
import toothpick.registries.FactoryRegistryLocator
import toothpick.registries.MemberInjectorRegistryLocator

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initLogger()
        initToothpick()
        initAppScope()
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initToothpick() {
//        if (BuildConfig.DEBUG) {
//            Toothpick.setConfiguration(Configuration.forDevelopment().disableReflection().preventMultipleRootScopes())
//        } else {
        Toothpick.setConfiguration(Configuration.forProduction().disableReflection())
        FactoryRegistryLocator.setRootRegistry(FactoryRegistry())
        MemberInjectorRegistryLocator.setRootRegistry(MemberInjectorRegistry())
//        }
    }

    private fun initAppScope() {
        Toothpick.openScope(DI.APP_SCOPE).installModules(AppModule(this))
        Toothpick.openScopes(DI.APP_SCOPE, DI.NETWORK_SCOPE).installModules(NetworkModule())
    }
}
