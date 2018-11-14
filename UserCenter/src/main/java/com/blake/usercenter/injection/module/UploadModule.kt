package com.blake.usercenter.injection.module

import com.blake.usercenter.service.UploadService
import com.blake.usercenter.service.UserService
import com.blake.usercenter.service.impl.UploadServiceImpl
import com.blake.usercenter.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

@Module
class UploadModule {
    @Provides
    fun providesUploadService(uploadService: UploadServiceImpl): UploadService = uploadService
}