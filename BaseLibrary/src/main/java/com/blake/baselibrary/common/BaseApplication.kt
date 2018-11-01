package com.blake.baselibrary.common

import android.app.Application
import com.blake.baselibrary.injection.component.AppComponent
import com.blake.baselibrary.injection.component.DaggerAppComponent
import com.blake.baselibrary.injection.module.AppModule

/**
 * Create by Pidan
 */
class BaseApplication : Application() {

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()

        initInjection()
    }

    private fun initInjection() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}