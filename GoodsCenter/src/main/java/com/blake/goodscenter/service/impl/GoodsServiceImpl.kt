package com.blake.goodscenter.service.impl

import com.blake.baselibrary.ext.convert
import com.blake.goodscenter.data.protocol.Goods
import com.blake.goodscenter.data.repository.GoodsRepository
import com.blake.goodscenter.service.GoodsService
import rx.Observable
import javax.inject.Inject

/**
 * Create by Pidan
 */
class GoodsServiceImpl @Inject constructor() : GoodsService {
    @Inject
    lateinit var repository: GoodsRepository

    override fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?> =
        repository.getGoodsList(categoryId, pageNo).convert()

    override fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<MutableList<Goods>?> =
        repository.getGoodsListByKeyword(keyword, pageNo).convert()

    override fun getGoodsDetail(goodsId: Int): Observable<Goods> =
        repository.getGoodsDetail(goodsId).convert()
}