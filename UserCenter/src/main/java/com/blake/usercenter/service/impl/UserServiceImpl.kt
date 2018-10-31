package com.blake.usercenter.service.impl

import com.blake.baselibrary.data.protocol.BaseResp
import com.blake.baselibrary.rx.BaseException
import com.blake.usercenter.data.repository.UserRepository
import com.blake.usercenter.service.UserService
import rx.Observable
import rx.functions.Func1

/**
 * Create by Pidan
 */
class UserServiceImpl : UserService {
    override fun register(mobile: String, verifyCode: String, psw: String): Observable<Boolean> {
        val repository = UserRepository()
        return repository.register(mobile, psw, verifyCode)
            .flatMap(object : Func1<BaseResp<String>, Observable<Boolean>> {
                override fun call(t: BaseResp<String>): Observable<Boolean> {
                    if (t.status != 0) {
                        return Observable.error(BaseException(t.status, t.message))
                    }
                    return Observable.just(true)
                }
            })
    }
}