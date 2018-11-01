package com.blake.baselibrary.injection.component

import android.content.Context
import com.blake.baselibrary.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * Create by Pidan
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun context(): Context
}