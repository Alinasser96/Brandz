package com.hamalawey.brandz.di

import com.hamalawey.domain.repository.BrandsRepo
import com.hamalawey.domain.usecase.GetBrandItemsFromRemote
import com.hamalawey.domain.usecase.GetItemDetailsFromRemote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetBrandItemsFromRemoteUseCase(brandsRepo: BrandsRepo) =
        GetBrandItemsFromRemote(brandsRepo)

    @Provides
    fun provideGetItemDetailsFromRemoteUseCase(brandsRepo: BrandsRepo) =
        GetItemDetailsFromRemote(brandsRepo)

}