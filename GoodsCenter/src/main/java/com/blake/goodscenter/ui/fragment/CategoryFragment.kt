package com.blake.goodscenter.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blake.baselibrary.ext.setVisible
import com.blake.baselibrary.ext.startLoading
import com.blake.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import com.blake.baselibrary.ui.fragment.BaseMvpFragment
import com.blake.goodscenter.R
import com.blake.goodscenter.common.GoodsConstant
import com.blake.goodscenter.data.protocol.Category
import com.blake.goodscenter.injection.component.DaggerCategoryComponent
import com.blake.goodscenter.injection.module.CategoryModule
import com.blake.goodscenter.presenter.CategoryPresenter
import com.blake.goodscenter.presenter.view.CategoryView
import com.blake.goodscenter.ui.activity.GoodsActivity
import com.blake.goodscenter.ui.adapter.SecondCategoryAdapter
import com.blake.goodscenter.ui.adapter.TopCategoryAdapter
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.fragment_category.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * Create by Pidan
 */
class CategoryFragment : BaseMvpFragment<CategoryPresenter>(), CategoryView {

    private lateinit var topAdapter: TopCategoryAdapter
    private lateinit var secondAdapter: SecondCategoryAdapter

    override fun injectComponent() {
        DaggerCategoryComponent.builder()
            .activityComponent(mActivityComponent)
            .categoryModule(CategoryModule())
            .build()
            .inject(this)
        mPresenter.mView = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData()
    }

    private fun loadData(parentId: Int = 0) {
        if (parentId != 0) {
            mMultiStateView.startLoading()
        }
        mPresenter.getCategory(parentId)

    }

    private fun initView() {
        mTopCategoryRv.layoutManager = LinearLayoutManager(context)
        topAdapter = TopCategoryAdapter(context!!)
        mTopCategoryRv.adapter = topAdapter
        topAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                for (category in topAdapter.dataList) {
                    category.isSelected = item.id == category.id
                }
                topAdapter.notifyDataSetChanged()
                loadData(item.id)
            }
        })
        mSecondCategoryRv.layoutManager = GridLayoutManager(context, 3)
        secondAdapter = SecondCategoryAdapter(context!!)
        mSecondCategoryRv.adapter = secondAdapter
        secondAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                startActivity<GoodsActivity>(GoodsConstant.KEY_CATEGORY_ID to item.id)
            }
        })
    }

    override fun onGetCategoryResult(result: MutableList<Category>?) {
        if (result != null && result.size > 0) {
            result?.let {
                if (result[0].parentId == 0) {
                    result[0].isSelected = true
                    topAdapter.setData(result)
                    loadData(result[0].id)
                } else {
                    mTopCategoryIv.setVisible(true)
                    mCategoryTitleTv.setVisible(true)
                    secondAdapter.setData(result)
                    mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
                }
            }
        } else {
            mTopCategoryIv.setVisible(false)
            mCategoryTitleTv.setVisible(false)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }
}