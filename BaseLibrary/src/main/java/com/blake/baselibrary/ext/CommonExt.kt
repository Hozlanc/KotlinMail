package com.blake.baselibrary.ext

import android.view.View
import com.blake.baselibrary.data.protocol.BaseResp
import com.blake.baselibrary.rx.BaseFunc
import com.blake.baselibrary.rx.BaseFuncBoolean
import com.blake.baselibrary.rx.BaseSubscriber
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

fun View.onClick(methed: () -> Unit) {
    this.setOnClickListener { methed() }
}