package com.blake.usercenter.service.impl

import com.blake.usercenter.service.UserService
import rx.Observable

/**
 * Create by Pidan
 */
class UserServiceImpl : UserService {
    override fun register(mobile: String, verifyCode: String, psw: String): Observable<Boolean> {
        return Observable.just(true)
    }
}