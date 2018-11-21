package com.blake.goodscenter.presenter.view

import com.blake.baselibrary.presenter.view.BaseView
import com.blake.goodscenter.data.protocol.Category

/**
 * Create by Pidan
 */
interface CategoryView : BaseView {
    fun onGetCategoryResult(result: MutableList<Category>?)
}