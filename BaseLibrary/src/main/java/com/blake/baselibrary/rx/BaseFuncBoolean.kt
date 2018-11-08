package com.blake.baselibrary.rx

import com.blake.baselibrary.common.ResultCode
import com.blake.baselibrary.data.protocol.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 * Create by Pidan
 */
class BaseFuncBoolean<T> : Func1<BaseResp<T>, Observable<Boolean>> {
    override fun call(t: BaseResp<T>): Observable<Boolean> {
        if (t.status != ResultCode.SUCCESS) {
            return Observable.error(BaseException(t.status, t.message))
        }
        return Observable.just(true)
    }
}