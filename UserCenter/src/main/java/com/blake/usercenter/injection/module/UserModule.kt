package com.blake.usercenter.injection.module

import com.blake.usercenter.service.UserService
import com.blake.usercenter.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Create by Pidan
 */
@Module
class UserModule {
    @Provides
    fun providesUserService(service: UserServiceImpl): UserService = service
}