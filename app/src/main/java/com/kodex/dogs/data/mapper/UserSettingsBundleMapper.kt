package com.kodex.dogs.data.mapper

import com.kodex.dogs.data.model.UserSettingsBundleDTO
import com.kodex.dogs.domain.model.UserSettingsBundle

fun UserSettingsBundleDTO.toDomain(): UserSettingsBundle {
    return UserSettingsBundle(
        personalData = personalData,
        addressData = addressData,
        userSettingsData = userSettingsData
    )
}