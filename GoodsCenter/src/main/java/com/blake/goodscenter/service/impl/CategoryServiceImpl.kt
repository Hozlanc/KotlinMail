package com.blake.goodscenter.service.impl

import com.blake.baselibrary.ext.convert
import com.blake.goodscenter.data.protocol.Category
import com.blake.goodscenter.data.repository.CategoryRepository
import com.blake.usercenter.service.CategoryService
import rx.Observable
import javax.inject.Inject

/**
 * Create by Pidan
 */
class CategoryServiceImpl @Inject constructor() : CategoryService {
    @Inject
    lateinit var repository: CategoryRepository

    override fun getCategory(parentId: Int): Observable<MutableList<Category>?> =
        repository.getCategory(parentId).convert()

}