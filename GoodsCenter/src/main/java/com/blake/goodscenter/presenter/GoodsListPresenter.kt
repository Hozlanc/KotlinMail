package com.blake.goodscenter.presenter

import com.blake.baselibrary.ext.execute
import com.blake.baselibrary.presenter.BasePresenter
import com.blake.baselibrary.rx.BaseSubscriber
import com.blake.goodscenter.data.protocol.Goods
import com.blake.goodscenter.presenter.view.GoodsListView
import com.blake.goodscenter.service.GoodsService
import javax.inject.Inject

/**
 * Create by Pidan
 */
class GoodsListPresenter @Inject constructor() : BasePresenter<GoodsListView>() {
    @Inject
    lateinit var goodsService: GoodsService

    fun getGoodsList(categoryId: Int, pageNo: Int) {
        if (!checkNetwork()) {
            return
        }
        mView.showLoading()
        goodsService.getGoodsList(categoryId, pageNo)
            .execute(lifecycleProvider, object : BaseSubscriber<MutableList<Goods>?>(mView) {
                override fun onNext(t: MutableList<Goods>?) {
                    mView.onGetGoodsListResult(t)
                }
            })
    }

    fun getGoodsListByKeyword(keyword: String, pageNo: Int) {
        if (!checkNetwork()) {
            return
        }
        mView.showLoading()
        goodsService.getGoodsListByKeyword(keyword, pageNo)
            .execute(lifecycleProvider, object : BaseSubscriber<MutableList<Goods>?>(mView) {
                override fun onNext(t: MutableList<Goods>?) {
                    mView.onGetGoodsListResult(t)
                }
            })
    }
}