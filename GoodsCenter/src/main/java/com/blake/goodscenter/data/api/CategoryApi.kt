package com.blake.goodscenter.data.api

import com.blake.baselibrary.data.protocol.BaseResp
import com.blake.goodscenter.data.protocol.Category
import com.blake.goodscenter.data.protocol.GetCategoryReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/*
    商品分类接口
 */
interface CategoryApi {
    /*
        获取商品分类列表
     */
    @POST("category/getCategory")
    fun getCategory(@Body req: GetCategoryReq): Observable<BaseResp<MutableList<Category>?>>
}
