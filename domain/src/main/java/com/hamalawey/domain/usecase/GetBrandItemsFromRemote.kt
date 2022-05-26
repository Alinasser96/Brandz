package com.hamalawey.domain.usecase

import com.hamalawey.domain.repository.BrandsRepo

class GetBrandItemsFromRemote(private val brandsRepo: BrandsRepo) {
    suspend fun invoke(page: Int, perPage: Int) = brandsRepo.getBrandItemsFromRemote(page, perPage)
}