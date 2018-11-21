package com.blake.goodscenter.data.repository

import com.blake.baselibrary.data.net.RetrofitFactory
import com.blake.baselibrary.data.protocol.BaseResp
import com.blake.goodscenter.data.api.CategoryApi
import com.blake.goodscenter.data.protocol.Category
import com.blake.goodscenter.data.protocol.GetCategoryReq
import rx.Observable
import javax.inject.Inject

/**
 * Create by Pidan
 */
class CategoryRepository @Inject constructor() {
    fun getCategory(parentId: Int): Observable<BaseResp<MutableList<Category>?>> =
        RetrofitFactory.create(CategoryApi::class.java).getCategory(GetCategoryReq(parentId))

}