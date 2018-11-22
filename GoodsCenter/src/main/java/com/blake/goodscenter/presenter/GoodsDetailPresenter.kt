package com.blake.goodscenter.presenter

import com.blake.baselibrary.ext.execute
import com.blake.baselibrary.presenter.BasePresenter
import com.blake.baselibrary.rx.BaseSubscriber
import com.blake.goodscenter.data.protocol.Goods
import com.blake.goodscenter.presenter.view.GoodsDetailView
import com.blake.goodscenter.service.GoodsService
import javax.inject.Inject

/**
 * Create by Pidan
 */
class GoodsDetailPresenter @Inject constructor() : BasePresenter<GoodsDetailView>() {
    @Inject
    lateinit var goodsService: GoodsService

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

                override fun onError(e: Throwable?) {
                    super.onError(e)
                    println(e?.message)
                }
            })
    }
}