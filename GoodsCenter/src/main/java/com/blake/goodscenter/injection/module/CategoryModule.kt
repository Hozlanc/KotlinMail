package com.blake.goodscenter.injection.module

import com.blake.goodscenter.service.impl.CategoryServiceImpl
import com.blake.usercenter.service.CategoryService
import dagger.Module
import dagger.Provides

/**
 * Create by Pidan
 */
@Module
class CategoryModule {
    @Provides
    fun providesCategoryService(service: CategoryServiceImpl): CategoryService = service
}