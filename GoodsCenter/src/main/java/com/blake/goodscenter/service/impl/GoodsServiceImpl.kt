package com.blake.goodscenter.service.impl

import com.blake.baselibrary.ext.convert
import com.blake.goodscenter.data.protocol.Goods
import com.blake.goodscenter.data.repository.GoodsRepository
import com.blake.usercenter.service.GoodsService
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
}