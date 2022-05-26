package com.hamalawey.data.repository

import com.hamalawey.data.remote.BrandsApi
import com.hamalawey.domain.entities.brand.BrandResponse
import com.hamalawey.domain.entities.item.ItemDetailsResponse
import com.hamalawey.domain.repository.BrandsRepo

class BrandsRepoImpl(
    private val brandsApi: BrandsApi
) : BrandsRepo {
    override suspend fun getBrandItemsFromRemote(page: Int, perPage: Int): BrandResponse {
        return brandsApi.getBrandItems(page, perPage)
    }

    override suspend fun getItemDetailsFromRemote(itemId: Int): ItemDetailsResponse {
        return brandsApi.getItemDetails(itemId)
    }
}