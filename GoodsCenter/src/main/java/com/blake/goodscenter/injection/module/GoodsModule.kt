package com.blake.goodscenter.injection.module

import com.blake.goodscenter.service.impl.GoodsServiceImpl
import com.blake.usercenter.service.GoodsService
import dagger.Module
import dagger.Provides

/**
 * Create by Pidan
 */
@Module
class GoodsModule {
    @Provides
    fun providesGoodsService(service: GoodsServiceImpl): GoodsService = service
}