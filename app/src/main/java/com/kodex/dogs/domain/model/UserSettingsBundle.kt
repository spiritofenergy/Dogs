package com.kodex.dogs.domain.model

data class UserSettingsBundle(
    val personalData: PersonalData,
    val addressData: AddressData,
    val userSettingsData: UserSettingsData
)
