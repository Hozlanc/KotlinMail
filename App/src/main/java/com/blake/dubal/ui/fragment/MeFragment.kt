package com.blake.dubal.ui.fragment

import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blake.baselibrary.common.BaseConstant
import com.blake.baselibrary.ext.loadUrl
import com.blake.baselibrary.ext.onClick
import com.blake.baselibrary.ui.fragment.BaseFragment
import com.blake.baselibrary.utils.AppPrefsUtils
import com.blake.baselibrary.widgets.BannerImageLoader
import com.blake.dubal.R
import com.blake.dubal.R.id.mUserIconIv
import com.blake.dubal.R.id.mUserNameTv
import com.blake.dubal.common.*
import com.blake.dubal.ui.activity.SettingActivity
import com.blake.dubal.ui.adapter.HomeDiscountAdapter
import com.blake.dubal.ui.adapter.TopicAdapter
import com.blake.provider.common.ProviderConstant
import com.blake.provider.common.afterLogin
import com.blake.provider.common.isLogined
import com.blake.usercenter.ui.activity.LoginActivity
import com.blake.usercenter.ui.activity.UserInfoActivity
import com.kotlin.order.common.OrderConstant
import com.kotlin.order.common.OrderStatus
import com.kotlin.order.ui.activity.OrderActivity
import com.kotlin.order.ui.activity.ShipAddressActivity
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_me.*
import me.crosswall.lib.coverflow.CoverFlow
import org.jetbrains.anko.support.v4.startActivity
import java.security.Provider

/**
 * Create by Pidan
 */
class MeFragment : BaseFragment(), View.OnClickListener {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_me, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    private fun initView() {
        mUserIconIv.onClick(this)
        mUserNameTv.onClick(this)
        mSettingTv.onClick(this)
        mAddressTv.onClick(this)
        mAllOrderTv.onClick(this)
        mWaitPayOrderTv.onClick(this)
        mWaitConfirmOrderTv.onClick(this)
        mCompleteOrderTv.onClick(this)
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        if (isLogined()) {
            val userIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
            if (userIcon.isNotEmpty()) {
                mUserIconIv.loadUrl(userIcon)
            }
            mUserNameTv.text = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        } else {
            mUserIconIv.setImageResource(R.drawable.icon_default_user)
            mUserNameTv.text = getString(R.string.un_login_text)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mUserIconIv, R.id.mUserNameTv -> afterLogin { startActivity<UserInfoActivity>() }
            R.id.mSettingTv -> afterLogin { startActivity<SettingActivity>() }
            R.id.mAddressTv -> afterLogin { startActivity<ShipAddressActivity>() }
            R.id.mAllOrderTv -> afterLogin { startActivity<OrderActivity>() }
            R.id.mWaitPayOrderTv -> afterLogin { startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_PAY) }
            R.id.mWaitConfirmOrderTv -> afterLogin { startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_CONFIRM) }
            R.id.mCompleteOrderTv -> afterLogin { startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_COMPLETED) }
        }
    }
}