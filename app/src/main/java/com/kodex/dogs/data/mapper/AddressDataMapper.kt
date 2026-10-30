package com.kodex.dogs.data.mapper

import com.kodex.dogs.data.model.AddressDataDTO
import com.kodex.dogs.domain.model.AddressData

fun AddressDataDTO.toAddressData(): AddressData {
    return AddressData(
        city = city,
        street = street,
        flat = flat,
        postCode = postCode
    )
}

fun AddressData.toDTO(): AddressDataDTO {
    return AddressDataDTO(
        city = city,
        street = street,
        flat = flat,
        postCode = postCode
    )
}