package com.blake.goodscenter.presenter

import com.blake.baselibrary.ext.execute
import com.blake.baselibrary.presenter.BasePresenter
import com.blake.baselibrary.rx.BaseSubscriber
import com.blake.baselibrary.utils.AppPrefsUtils
import com.blake.goodscenter.common.GoodsConstant
import com.blake.goodscenter.data.protocol.Goods
import com.blake.goodscenter.presenter.view.GoodsDetailView
import com.blake.goodscenter.service.CartService
import com.blake.goodscenter.service.GoodsService
import javax.inject.Inject

/**
 * Create by Pidan
 */
class GoodsDetailPresenter @Inject constructor() : BasePresenter<GoodsDetailView>() {
    @Inject
    lateinit var goodsService: GoodsService

    @Inject
    lateinit var cartService: CartService

    fun getGoodsDetail(goodsId: Int) {
        if (!checkNetwork()) {
            return
        }
        mView.showLoading()
        goodsService.getGoodsDetail(goodsId)
            .execute(lifecycleProvider, object : BaseSubscriber<Goods>(mView) {
                override fun onNext(t: Goods) {
                    mView.onGetGoodsDetailResult(t)
                }
            })
    }

    fun addCart(
        goodsId: Int,
        goodsDesc: String,
        goodsIcon: String,
        goodsPrice: Long,
        goodsCount: Int,
        goodsSku: String
    ) {
        if (!checkNetwork()) {
            return
        }
        mView.showLoading()
        cartService.addCart(goodsId, goodsDesc, goodsIcon, goodsPrice, goodsCount, goodsSku)
            .execute(lifecycleProvider, object : BaseSubscriber<Int>(mView) {
                override fun onNext(t: Int) {
                    AppPrefsUtils.putInt(GoodsConstant.SP_CART_SIZE, t)
                    mView.onAddCartResult(t)
                }
            })
    }
}