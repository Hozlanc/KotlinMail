package com.blake.goodscenter.presenter

import com.blake.baselibrary.ext.execute
import com.blake.baselibrary.presenter.BasePresenter
import com.blake.baselibrary.rx.BaseSubscriber
import com.blake.goodscenter.data.protocol.Category
import com.blake.goodscenter.presenter.view.CategoryView
import com.blake.usercenter.service.CategoryService
import javax.inject.Inject

/**
 * Create by Pidan
 */
class CategoryPresenter @Inject constructor() : BasePresenter<CategoryView>() {
    @Inject
    lateinit var categoryService: CategoryService

    fun getCategory(parentId: Int) {
        if (!checkNetwork()) {
            return
        }
        mView.showLoading()
        categoryService.getCategory(parentId)
            .execute(lifecycleProvider, object : BaseSubscriber<MutableList<Category>?>(mView) {
                override fun onNext(t: MutableList<Category>?) {
                    mView.onGetCategoryResult(t)
                }

                override fun onError(e: Throwable?) {
                    super.onError(e)
                    println(e?.message)
                }
            })
    }
}