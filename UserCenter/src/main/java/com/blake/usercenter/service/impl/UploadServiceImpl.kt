package com.blake.usercenter.service.impl

import com.blake.baselibrary.ext.convert
import com.blake.usercenter.data.repository.UploadRepository
import com.blake.usercenter.service.UploadService
import rx.Observable
import javax.inject.Inject

class UploadServiceImpl @Inject constructor() : UploadService {

    @Inject
    lateinit var repository: UploadRepository

    override fun getUploadToken(): Observable<String> {
        return repository.getUploadToken().convert()
    }
}