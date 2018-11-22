package com.blake.goodscenter.ui.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.blake.baselibrary.ext.onClick
import com.blake.baselibrary.ui.activity.BaseActivity
import com.blake.baselibrary.ui.fragment.BaseMvpFragment
import com.blake.baselibrary.widgets.BannerImageLoader
import com.blake.goodscenter.R
import com.blake.goodscenter.common.GoodsConstant
import com.blake.goodscenter.data.protocol.Goods
import com.blake.goodscenter.event.GoodsDetailImageEvent
import com.blake.goodscenter.event.SkuChangedEvent
import com.blake.goodscenter.injection.component.DaggerGoodsComponent
import com.blake.goodscenter.injection.module.GoodsModule
import com.blake.goodscenter.presenter.GoodsDetailPresenter
import com.blake.goodscenter.presenter.view.GoodsDetailView
import com.blake.goodscenter.ui.activity.GoodsDetailActivity
import com.blake.goodscenter.widget.GoodsSkuPopView
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kotlin.base.utils.YuanFenConverter
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*

/**
 * Create by Pidan
 */
class GoodsDetailTabOneFragment : BaseMvpFragment<GoodsDetailPresenter>(), GoodsDetailView {

    private lateinit var mSkuPop: GoodsSkuPopView
    private lateinit var mAnimStart: ScaleAnimation
    private lateinit var mAnimEnd: ScaleAnimation

    override fun injectComponent() {
        DaggerGoodsComponent.builder()
            .activityComponent(mActivityComponent)
            .goodsModule(GoodsModule())
            .build()
            .inject(this)
        mPresenter.mView = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_goods_detail_tab_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAnim()
        initSkuPop()
        loadData()
        initObserve()
    }

    private fun initView() {
        mGoodsDetailBanner.setImageLoader(BannerImageLoader())
        mGoodsDetailBanner.setBannerAnimation(Transformer.Default)
        mGoodsDetailBanner.setDelayTime(2000)
        //设置指示器位置（当banner模式中有指示器时）
        mGoodsDetailBanner.setIndicatorGravity(BannerConfig.RIGHT)

        mSkuView.onClick {
            mSkuPop.showAtLocation(
                (activity as BaseActivity).contentView,
                Gravity.BOTTOM and Gravity.CENTER_HORIZONTAL,
                0,
                0
            )
            (activity as BaseActivity).contentView.startAnimation(mAnimStart)
        }
    }

    private fun initAnim() {
        mAnimStart =
                ScaleAnimation(1f, 0.95f, 1f, 0.95f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimStart.duration = 500
        mAnimStart.fillAfter = true

        mAnimEnd =
                ScaleAnimation(0.95f, 1f, 0.95f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimEnd.duration = 500
        mAnimEnd.fillAfter = true
    }

    private fun initSkuPop() {
        mSkuPop = GoodsSkuPopView(activity!!)
        mSkuPop.setOnDismissListener {
            (activity as BaseActivity).contentView.startAnimation(mAnimEnd)
        }
    }

    private fun loadData() {
        mPresenter.getGoodsDetail(activity!!.intent.getIntExtra(GoodsConstant.KEY_GOODS_ID, -1))
    }

    private fun initObserve() {
        Bus.observe<SkuChangedEvent>()
            .subscribe {
                mSkuSelectedTv.text = mSkuPop.getSelectSku() + GoodsConstant.SKU_SEPARATOR + mSkuPop.getSelectCount() +
                        "件"
            }.registerInBus(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    override fun onGetGoodsDetailResult(result: Goods) {
        mGoodsDetailBanner.setImages(result.goodsBanner.split(","))
        //banner设置方法全部调用完毕时最后调用
        mGoodsDetailBanner.start()

        mGoodsDescTv.text = result.goodsDesc
        mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice)
        mSkuSelectedTv.text = result.goodsDefaultSku

        Bus.send(GoodsDetailImageEvent(result.goodsDetailOne, result.goodsDetailTwo))
        loadPopData(result)
    }

    private fun loadPopData(result: Goods) {
        mSkuPop.setGoodsIcon(result.goodsDefaultIcon)
        mSkuPop.setGoodsCode(result.goodsCode)
        mSkuPop.setGoodsPrice(result.goodsDefaultPrice)

        mSkuPop.setSkuData(result.goodsSku)
    }
}