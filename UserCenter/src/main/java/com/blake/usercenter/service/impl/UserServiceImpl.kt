package com.blake.usercenter.service.impl

import com.blake.baselibrary.data.protocol.BaseResp
import com.blake.baselibrary.ext.convertBoolean
import com.blake.baselibrary.rx.BaseException
import com.blake.baselibrary.rx.BaseFuncBoolean
import com.blake.usercenter.data.repository.UserRepository
import com.blake.usercenter.service.UserService
import rx.Observable
import rx.functions.Func1
import javax.inject.Inject

/**
 * Create by Pidan
 */
class UserServiceImpl @Inject constructor() : UserService {
    @Inject
    lateinit var repository: UserRepository

    override fun register(mobile: String, verifyCode: String, psw: String): Observable<Boolean> {
        return repository.register(mobile, psw, verifyCode)
            .convertBoolean()
    }
}