package com.blake.usercenter.data.repository

import com.blake.baselibrary.data.net.RetrofitFactory
import com.blake.baselibrary.data.protocol.BaseResp
import com.blake.usercenter.data.api.UserApi
import com.blake.usercenter.data.protocol.RegisterReq
import rx.Observable
import javax.inject.Inject

/**
 * Create by Pidan
 */
class UserRepository @Inject constructor() {
    fun register(mobile: String, psw: String, verifyCode: String) =
        RetrofitFactory.create(UserApi::class.java)
            .register(RegisterReq(mobile, psw, verifyCode))

}