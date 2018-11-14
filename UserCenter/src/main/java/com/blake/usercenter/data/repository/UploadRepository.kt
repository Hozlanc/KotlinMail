package com.blake.usercenter.data.repository

import com.blake.baselibrary.data.net.RetrofitFactory
import com.blake.baselibrary.data.protocol.BaseResp
import com.blake.usercenter.data.api.UploadApi
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
class UploadRepository @Inject constructor() {
    fun getUploadToken() = RetrofitFactory.create(UploadApi::class.java).getUploadToken()
}