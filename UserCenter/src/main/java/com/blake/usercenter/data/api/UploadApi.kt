package com.blake.usercenter.data.api

import com.blake.baselibrary.data.protocol.BaseResp
import com.blake.usercenter.data.protocol.*
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * Create by Pidan
 */
interface UploadApi {
    @POST("common/getUploadToken")
    fun getUploadToken(): Observable<BaseResp<String>>
}