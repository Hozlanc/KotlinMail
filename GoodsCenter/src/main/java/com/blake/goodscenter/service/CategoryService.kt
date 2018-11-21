package com.blake.usercenter.service

import com.blake.goodscenter.data.protocol.Category
import rx.Observable

/**
 * Create by Pidan
 */
interface CategoryService {
    fun getCategory(parentId: Int): Observable<MutableList<Category>?>
}