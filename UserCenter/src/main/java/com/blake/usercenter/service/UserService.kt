package com.blake.usercenter.service

import rx.Observable

/**
 * Create by Pidan
 */
interface UserService {
    fun register(mobile: String, verifyCode: String, psw: String):Observable<Boolean>
}