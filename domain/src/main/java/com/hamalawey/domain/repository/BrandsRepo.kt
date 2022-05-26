package com.hamalawey.domain.repository

import com.hamalawey.domain.entities.brand.BrandResponse
import com.hamalawey.domain.entities.item.ItemDetailsResponse

interface BrandsRepo {
    suspend fun getBrandItemsFromRemote(page: Int, perPage: Int): BrandResponse
    suspend fun getItemDetailsFromRemote(itemId: Int): ItemDetailsResponse
}