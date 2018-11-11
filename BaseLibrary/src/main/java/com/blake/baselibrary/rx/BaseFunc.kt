package com.blake.baselibrary.rx

import com.blake.baselibrary.common.ResultCode
import com.blake.baselibrary.data.protocol.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 * Create by Pidan
 */
class BaseFunc<T> : Func1<BaseResp<T>, Observable<T>> {
    override fun call(t: BaseResp<T>): Observable<T> {
        if (t.status != ResultCode.SUCCESS) {
            return Observable.error(BaseException(t.status, t.message))
        }
        return Observable.just(t.data)
    }
}