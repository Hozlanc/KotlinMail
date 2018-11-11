package com.blake.usercenter.data.repository

import com.blake.baselibrary.data.net.RetrofitFactory
import com.blake.baselibrary.data.protocol.BaseResp
import com.blake.usercenter.data.api.UserApi
import com.blake.usercenter.data.protocol.ForgetPwdReq
import com.blake.usercenter.data.protocol.LoginReq
import com.blake.usercenter.data.protocol.RegisterReq
import com.blake.usercenter.data.protocol.ResetPwdReq
import rx.Observable
import javax.inject.Inject

/**
 * Create by Pidan
 */
class UserRepository @Inject constructor() {
    fun register(mobile: String, psw: String, verifyCode: String) =
        RetrofitFactory.create(UserApi::class.java)
            .register(RegisterReq(mobile, psw, verifyCode))


    fun login(mobile: String, psw: String, pushId: String) =
        RetrofitFactory.create(UserApi::class.java)
            .login(LoginReq(mobile, psw, pushId))

    fun forgetPwd(mobile: String, verifyCode: String) =
        RetrofitFactory.create(UserApi::class.java)
            .forgetPwd(ForgetPwdReq(mobile, verifyCode))

    fun resetPwd(mobile: String, psw: String) =
        RetrofitFactory.create(UserApi::class.java)
            .resetPwd(ResetPwdReq(mobile, psw))
}