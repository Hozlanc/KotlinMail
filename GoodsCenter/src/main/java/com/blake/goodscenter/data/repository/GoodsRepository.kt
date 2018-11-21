package com.blake.goodscenter.data.repository

import com.blake.baselibrary.data.net.RetrofitFactory
import com.blake.baselibrary.data.protocol.BaseResp
import com.blake.goodscenter.data.api.GoodsApi
import com.blake.goodscenter.data.protocol.GetGoodsDetailReq
import com.blake.goodscenter.data.protocol.GetGoodsListByKeywordReq
import com.blake.goodscenter.data.protocol.GetGoodsListReq
import com.blake.goodscenter.data.protocol.Goods
import rx.Observable
import javax.inject.Inject

/*
    商品数据层
 */
class GoodsRepository @Inject constructor() {

    /*
        根据分类搜索商品
     */
    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<BaseResp<MutableList<Goods>?>> {
        return RetrofitFactory.create(GoodsApi::class.java).getGoodsList(GetGoodsListReq(categoryId, pageNo))
    }

    /*
        根据关键字搜索商品
     */
    fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<BaseResp<MutableList<Goods>?>> {
        return RetrofitFactory.create(GoodsApi::class.java)
            .getGoodsListByKeyword(GetGoodsListByKeywordReq(keyword, pageNo))
    }

    /*
        商品详情
     */
    fun getGoodsDetail(goodsId: Int): Observable<BaseResp<Goods>> {
        return RetrofitFactory.create(GoodsApi::class.java).getGoodsDetail(GetGoodsDetailReq(goodsId))
    }
}
