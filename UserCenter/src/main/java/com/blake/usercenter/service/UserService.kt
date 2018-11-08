package com.blake.usercenter.service

import com.blake.usercenter.data.protocol.UserInfo
import rx.Observable

/**
 * Create by Pidan
 */
interface UserService {
    fun register(mobile: String, psw: String, verifyCode: String): Observable<Boolean>
    fun login(mobile: String, psw: String, pushId: String): Observable<UserInfo>
}