package com.blake.baselibrary.injection.component

import android.app.Activity
import com.blake.baselibrary.injection.ActivityScope
import com.blake.baselibrary.injection.module.ActivityModule
import com.blake.baselibrary.injection.module.AppModule
import com.blake.baselibrary.injection.module.LifecycleProviderModule
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Component

/**
 * Create by Pidan
 */
@ActivityScope
@Component(
    modules = [ActivityModule::class, LifecycleProviderModule::class],
    dependencies = [AppComponent::class]
)
interface ActivityComponent {
    fun Activity(): Activity

    fun LifecycleProvider(): LifecycleProvider<*>
}