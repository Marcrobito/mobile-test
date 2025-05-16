package com.superformula.mobiletest.network

import com.superformula.mobiletest.entities.NetworkResponse
import com.superformula.mobiletest.entities.Seed

interface QRRepository {
    suspend fun getSeed():NetworkResponse<Seed>
}