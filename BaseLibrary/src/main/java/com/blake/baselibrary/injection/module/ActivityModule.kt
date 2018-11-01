package com.blake.baselibrary.injection.module

import android.app.Activity
import android.content.Context
import com.blake.baselibrary.common.BaseApplication
import com.blake.baselibrary.injection.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Create by Pidan
 */
@Module
class ActivityModule(private val activity: Activity) {

    @ActivityScope
    @Provides
    fun providesActivity(): Activity {
        return activity
    }
}