package com.superformula.mobiletest.network

import com.superformula.mobiletest.entities.SeedDto
import retrofit2.http.GET

interface QRApi {
    @GET("seed")
    suspend fun getStories(): SeedDto
}