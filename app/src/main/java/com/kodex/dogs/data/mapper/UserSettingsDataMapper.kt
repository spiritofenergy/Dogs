package com.kodex.dogs.data.mapper

import com.kodex.dogs.data.model.UserSettingsDataDTO
import com.kodex.dogs.domain.model.UserSettingsData

fun UserSettingsDataDTO.toUserSettingData(): UserSettingsData{
    return UserSettingsData(
        imageFormat = imageFormat,
        quality = quality,
        size = size,
    )
}
fun UserSettingsData.toDTO(): UserSettingsDataDTO{
    return UserSettingsDataDTO(
        imageFormat = imageFormat,
        quality = quality,
        size = size,
    )
}
