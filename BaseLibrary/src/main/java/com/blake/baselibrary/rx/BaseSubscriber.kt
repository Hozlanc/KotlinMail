package com.blake.baselibrary.rx

import rx.Subscriber

/**
 * Create by Pidan
 */
open class BaseSubscriber<T> : Subscriber<T>() {
    override fun onNext(t: T) {
    }

    override fun onCompleted() {
    }

    override fun onError(e: Throwable?) {
    }
}