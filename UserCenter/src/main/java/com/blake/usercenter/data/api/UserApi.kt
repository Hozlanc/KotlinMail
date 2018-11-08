package com.blake.usercenter.data.api

import com.blake.baselibrary.data.protocol.BaseResp
import com.blake.usercenter.data.protocol.LoginReq
import com.blake.usercenter.data.protocol.RegisterReq
import com.blake.usercenter.data.protocol.UserInfo
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * Create by Pidan
 */
interface UserApi {
    @POST("userCenter/register")
    fun register(@Body req: RegisterReq): Observable<BaseResp<String>>

    @POST("userCenter/login")
    fun login(@Body req: LoginReq): Observable<BaseResp<UserInfo>>
}