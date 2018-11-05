package com.blake.baselibrary.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import com.blake.baselibrary.common.AppManager
import com.trello.rxlifecycle.components.support.RxAppCompatActivity

/**
 * Create by Pidan
 */
open class BaseActivity : RxAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        AppManager.add(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.remove(this)
    }
}