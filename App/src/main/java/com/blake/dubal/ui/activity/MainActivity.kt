package com.blake.dubal.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.blake.dubal.R
import android.support.v4.app.Fragment
import com.blake.dubal.ui.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var pressTime: Long = 0
    //Fragment 栈管理
    private val mStack = Stack<Fragment>()
    //主界面Fragment
    private val mHomeFragment by lazy { HomeFragment() }
    //商品分类Fragment
//    private val mCategoryFragment by lazy { CategoryFragment() }
//    //购物车Fragment
//    private val mCartFragment by lazy { CartFragment() }
//    //消息Fragment
//    private val mMsgFragment by lazy { MessageFragment() }
//    //"我的"Fragment
//    private val mMeFragment by lazy { MeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        val manager = supportFragmentManager.beginTransaction()
        manager.replace(R.id.mContainer, mHomeFragment)
        manager.commit()
    }
}
