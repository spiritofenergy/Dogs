package com.kodex.dogs.domain.repository

import com.kodex.dogs.domain.model.AddressData
import com.kodex.dogs.domain.model.PersonalData
import com.kodex.dogs.domain.model.UserSettingsBundle
import com.kodex.dogs.domain.model.UserSettingsData

interface UserSettingsRepo {
    suspend fun insertPersonalData(personalData: PersonalData): Result<Unit>
    suspend fun insertAddressData(addressData: AddressData): Result<Unit>
    suspend fun insertUserSettingsData(userSettingsData: UserSettingsData): Result<Unit>
    suspend fun updateLastVisit(): Result<Unit>
    suspend fun getSettings(): Result<UserSettingsBundle>
}