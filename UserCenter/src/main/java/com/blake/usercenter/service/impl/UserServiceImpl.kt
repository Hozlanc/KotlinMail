package com.blake.usercenter.service.impl

import com.blake.baselibrary.ext.convert
import com.blake.baselibrary.ext.convertBoolean
import com.blake.usercenter.data.protocol.UserInfo
import com.blake.usercenter.data.repository.UserRepository
import com.blake.usercenter.service.UserService
import rx.Observable
import javax.inject.Inject

/**
 * Create by Pidan
 */
class UserServiceImpl @Inject constructor() : UserService {
    @Inject
    lateinit var repository: UserRepository

    override fun register(mobile: String, psw: String, verifyCode: String): Observable<Boolean> {
        return repository.register(mobile, psw, verifyCode).convertBoolean()
    }

    override fun login(mobile: String, psw: String, pushId: String): Observable<UserInfo> {
        return repository.login(mobile, psw, pushId).convert()
    }
}