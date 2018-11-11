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

    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {
        return repository.register(mobile, pwd, verifyCode).convertBoolean()
    }

    override fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo> {
        return repository.login(mobile, pwd, pushId).convert()
    }

    override fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean> {
        return repository.forgetPwd(mobile, verifyCode).convertBoolean()
    }

    override fun resetPwd(mobile: String, pwd: String): Observable<Boolean> {
        return repository.resetPwd(mobile, pwd).convertBoolean()
    }

}