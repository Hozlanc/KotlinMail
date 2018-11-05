package com.blake.baselibrary.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.blake.baselibrary.R
import kotlinx.android.synthetic.main.layout_header_bar.view.*

/**
 * Create by Pidan
 */
class HeaderBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var isShowBack = true
    private var titleText: String?
    private var rightText: String?

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderBar)
        isShowBack = typedArray.getBoolean(R.styleable.HeaderBar_isShowBack, true)
        titleText = typedArray.getString(R.styleable.HeaderBar_titleText)
        rightText = typedArray.getString(R.styleable.HeaderBar_rightText)

        initView()
        typedArray.recycle()
    }

    private fun initView() {
        View.inflate(context, R.layout.layout_header_bar, this)
        mLeftIv.visibility = if (isShowBack) View.VISIBLE else View.GONE
        titleText?.let { mTitleTv.text = it }
        rightText?.let {
            mRightTv.text = it
            mRightTv.visibility = View.VISIBLE
        }
    }
}