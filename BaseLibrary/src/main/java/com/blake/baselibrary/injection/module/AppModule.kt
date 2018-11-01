package com.blake.baselibrary.injection.module

import android.content.Context
import com.blake.baselibrary.common.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Create by Pidan
 */
@Module
class AppModule(private val context: BaseApplication) {

    @Singleton
    @Provides
    fun providesContext(): Context {
        return context
    }
}