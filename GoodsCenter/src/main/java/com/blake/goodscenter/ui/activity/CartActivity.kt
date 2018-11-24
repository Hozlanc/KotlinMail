package com.blake.goodscenter.ui.activity

import android.os.Bundle
import com.blake.baselibrary.ui.activity.BaseActivity
import com.blake.goodscenter.R
import com.blake.goodscenter.ui.fragment.CartFragment

/**
 * Create by Pidan
 */
class CartActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_cart) as CartFragment
        fragment.setBackVisible(true)
    }
}