package com.blake.goodscenter.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.alibaba.android.arouter.launcher.ARouter
import com.blake.baselibrary.ext.onClick
import com.blake.baselibrary.ui.activity.BaseActivity
import com.blake.goodscenter.R
import com.blake.goodscenter.ui.adapter.GoodsDetailVpAdapter
import com.blake.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_goods_detail.*

/**
 * Create by Pidan
 */
class GoodsDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)

        initView()
    }

    private fun initView() {
        mGoodsDetailTab.tabMode = TabLayout.MODE_FIXED
        mGoodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager, this)
        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)
        mAddCartBtn.onClick { ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGIN).navigation() }
    }
}