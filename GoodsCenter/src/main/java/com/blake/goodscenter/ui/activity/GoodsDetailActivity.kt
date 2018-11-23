package com.blake.goodscenter.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.Gravity
import com.blake.baselibrary.ext.onClick
import com.blake.baselibrary.ui.activity.BaseActivity
import com.blake.baselibrary.utils.AppPrefsUtils
import com.blake.goodscenter.R
import com.blake.goodscenter.common.GoodsConstant
import com.blake.goodscenter.event.AddCartEvent
import com.blake.goodscenter.event.UpdateCartSizeEvent
import com.blake.goodscenter.ui.adapter.GoodsDetailVpAdapter
import com.blake.provider.common.afterLogin
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import kotlinx.android.synthetic.main.activity_goods_detail.*
import org.jetbrains.anko.startActivity
import q.rorbin.badgeview.QBadgeView

/**
 * Create by Pidan
 */
class GoodsDetailActivity : BaseActivity() {

    private lateinit var mCartBadge: QBadgeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)

        initView()
        initObserve()
        loadCartSize()
    }

    private fun initView() {
        mGoodsDetailTab.tabMode = TabLayout.MODE_FIXED
        mGoodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager, this)
        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)
        mAddCartBtn.onClick {
            afterLogin { Bus.send(AddCartEvent()) }
        }
        mEnterCartTv.onClick {
            startActivity<CartActivity>()
        }
        mLeftIv.onClick {
            finish()
        }
        mCartBadge = QBadgeView(this)
    }

    private fun initObserve() {
        Bus.observe<UpdateCartSizeEvent>()
            .subscribe { setCartBadge() }
            .registerInBus(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    private fun setCartBadge() {
        mCartBadge.badgeGravity = Gravity.END or Gravity.TOP
        mCartBadge.setGravityOffset(22f, -2f, true)
        mCartBadge.setBadgeTextSize(6f, true)
        mCartBadge.bindTarget(mEnterCartTv).badgeNumber = AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE)
    }

    private fun loadCartSize() {
        setCartBadge()
    }
}