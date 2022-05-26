package com.hamalawey.brandz.di

import com.hamalawey.data.remote.BrandsApi
import com.hamalawey.data.repository.BrandsRepoImpl
import com.hamalawey.domain.repository.BrandsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideBrandsRepository(
        brandsApi: BrandsApi
    ): BrandsRepo =
        BrandsRepoImpl(brandsApi)
}