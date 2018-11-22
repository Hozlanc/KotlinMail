package com.blake.goodscenter.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blake.baselibrary.ext.loadUrl
import com.blake.baselibrary.ui.fragment.BaseFragment
import com.blake.goodscenter.R
import com.blake.goodscenter.event.GoodsDetailImageEvent
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_two.*

/**
 * Create by Pidan
 */
class GoodsDetailTabTwoFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_goods_detail_tab_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserve()
    }

    private fun initObserve() {
        Bus.observe<GoodsDetailImageEvent>()
            .subscribe { t: GoodsDetailImageEvent ->
                run {
                    mGoodsDetailOneIv.loadUrl(t.imgOne)
                    mGoodsDetailTwoIv.loadUrl(t.imgTwo)
                }
            }
            .registerInBus(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}