package com.blake.baselibrary.injection.module

import android.app.Activity
import android.content.Context
import com.blake.baselibrary.common.BaseApplication
import com.blake.baselibrary.injection.ActivityScope
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Module
import dagger.Provides

/**
 * Create by Pidan
 */
@Module
class LifecycleProviderModule(private val lifecycleProvider: LifecycleProvider<*>) {

    @Provides
    fun providesLifecycleProvider(): LifecycleProvider<*> {
        return lifecycleProvider
    }
}