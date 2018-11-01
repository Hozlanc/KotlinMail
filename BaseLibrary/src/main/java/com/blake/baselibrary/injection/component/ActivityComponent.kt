package com.blake.baselibrary.injection.component

import com.blake.baselibrary.injection.ActivityScope
import com.blake.baselibrary.injection.module.ActivityModule
import com.blake.baselibrary.injection.module.AppModule
import dagger.Component

/**
 * Create by Pidan
 */
@ActivityScope
@Component(modules = arrayOf(ActivityModule::class), dependencies = arrayOf(AppComponent::class))
interface ActivityComponent {
}