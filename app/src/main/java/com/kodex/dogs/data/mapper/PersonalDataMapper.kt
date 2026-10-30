package com.kodex.dogs.data.mapper

import com.kodex.dogs.data.model.PersonalDataDTO
import com.kodex.dogs.domain.model.PersonalData

fun PersonalDataDTO.toPersonalData(): PersonalData {
    return PersonalData(
        name = name,
        phone = phone,
        lastVisit = lastVisit,
    )
}
fun PersonalData.toDTO(): PersonalDataDTO {
    return PersonalDataDTO(
        name = name,
        phone = phone,
        lastVisit = lastVisit,
    )
}