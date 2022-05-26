package com.hamalawey.data.remote

import com.hamalawey.domain.entities.brand.BrandResponse
import com.hamalawey.domain.entities.item.ItemDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface BrandsApi {
    @Headers(
        "Currency: SAR",
        "AppVersion: 3.0.0",
        "Store-Identifier: 1328842359"
    )
    @GET("brands/259940351")
    suspend fun getBrandItems(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): BrandResponse

    @Headers(
        "Currency: SAR",
        "AppVersion: 3.0.0",
        "Store-Identifier: 1328842359"
    )
    @GET("products/{item_id}/details")
    suspend fun getItemDetails(
        @Path("item_id") itemId: Int
    ): ItemDetailsResponse
}