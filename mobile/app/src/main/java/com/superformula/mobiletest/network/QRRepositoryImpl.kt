package com.superformula.mobiletest.network

import com.superformula.mobiletest.entities.NetworkResponse
import com.superformula.mobiletest.entities.Seed
import com.superformula.mobiletest.entities.toSeed

class QRRepositoryImpl(private val api: QRApi) : QRRepository {
    override suspend fun getSeed(): NetworkResponse<Seed> {
        return try {
            NetworkResponse.Success(api.getStories().toSeed())
        } catch (e: Exception) {
            NetworkResponse.Error(e.message ?: "The request has Failed")
        }
    }
}