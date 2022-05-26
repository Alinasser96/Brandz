package com.hamalawey.domain.usecase

import com.hamalawey.domain.repository.BrandsRepo

class GetItemDetailsFromRemote(private val brandsRepo: BrandsRepo) {
    suspend fun invoke(itemId: Int) = brandsRepo.getItemDetailsFromRemote(itemId)
}