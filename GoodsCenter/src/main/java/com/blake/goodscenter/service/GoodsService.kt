package com.blake.usercenter.service

import com.blake.goodscenter.data.protocol.Goods
import rx.Observable

/**
 * Create by Pidan
 */
interface GoodsService {
    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?>
}