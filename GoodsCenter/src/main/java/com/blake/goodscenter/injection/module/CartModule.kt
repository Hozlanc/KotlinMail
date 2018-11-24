package com.blake.goodscenter.injection.module

import com.blake.goodscenter.service.CartService
import com.blake.goodscenter.service.impl.CartServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Create by Pidan
 */
@Module
class CartModule {
    @Provides
    fun providesCartService(service: CartServiceImpl): CartService = service
}