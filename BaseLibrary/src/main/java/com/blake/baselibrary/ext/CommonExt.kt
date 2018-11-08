package com.blake.baselibrary.ext

import android.view.View
import android.widget.Button
import android.widget.EditText
import com.blake.baselibrary.data.protocol.BaseResp
import com.blake.baselibrary.rx.BaseFunc
import com.blake.baselibrary.rx.BaseFuncBoolean
import com.blake.baselibrary.rx.BaseSubscriber
import com.blake.baselibrary.widgets.DefaultTextWatcher
import com.trello.rxlifecycle.LifecycleProvider
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Create by Pidan
 */
fun <T> Observable<T>.execute(lifecycleProvider: LifecycleProvider<*>, subscriber: BaseSubscriber<T>) {
    this.subscribeOn(Schedulers.io())
        .compose(lifecycleProvider.bindToLifecycle())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(subscriber)
}

fun <T> Observable<BaseResp<T>>.convert(): Observable<T> = this.flatMap(BaseFunc())

fun <T> Observable<BaseResp<T>>.convertBoolean(): Observable<Boolean> = this.flatMap(BaseFuncBoolean())

fun View.onClick(listener:View.OnClickListener):View{
    setOnClickListener(listener)
    return this
}

/*
    扩展点击事件
 */
fun View.onClick(methed: () -> Unit): View {
    setOnClickListener { methed() }
    return this
}

/*
    扩展点击事件，参数为方法
 */
fun Button.enable(et: EditText, method: () -> Boolean) {
    val btn: Button = this
    et.addTextChangedListener(object : DefaultTextWatcher() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            btn.isEnabled = method()
        }
    })
}