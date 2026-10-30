package com.kodex.dogs.data.model

import com.kodex.dogs.domain.model.AddressData
import com.kodex.dogs.domain.model.PersonalData
import com.kodex.dogs.domain.model.UserSettingsData

data class UserSettingsBundleDTO(
    val personalData: PersonalData,
    val addressData: AddressData,
    val userSettingsData: UserSettingsData
)
