package com.example.applicationsimple.data.repositories

import com.example.applicationsimple.data.models.BankDepartment

interface BankRepository {
    suspend fun getBanksInCity(city: String): List<BankDepartment>
}