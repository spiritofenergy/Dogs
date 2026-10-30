package com.kodex.dogs.data.mapper

import com.kodex.dogs.data.model.RatingDataDTO
import com.kodex.dogs.domain.model.RatingData

fun RatingDataDTO.toRatingData(): RatingData {
    return RatingData(
        id = id,
        name = name,
        uid = uid,
        rating = rating,
        lastRating = lastRating,
        message = message,
        timestamp = timestamp,
        bookId = bookId,
    )
}
fun RatingData.toDTO  (): RatingDataDTO {
    return RatingDataDTO(
        id = id,
        name = name,
        uid = uid,
        rating = rating,
        lastRating = lastRating,
        message = message,
        timestamp = timestamp,
        bookId = bookId,
    )
}