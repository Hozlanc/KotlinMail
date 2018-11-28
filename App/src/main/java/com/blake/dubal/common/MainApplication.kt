package com.blake.dubal.common

import cn.jpush.android.api.JPushInterface
import com.blake.baselibrary.common.BaseApplication

/**
 * Create by Pidan
 */
class MainApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()

        JPushInterface.setDebugMode(true)
        JPushInterface.init(this)
    }
}