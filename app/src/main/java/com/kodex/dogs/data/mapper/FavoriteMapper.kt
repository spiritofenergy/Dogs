package com.kodex.dogs.data.mapper

import com.kodex.dogs.domain.model.Favorite

fun Favorite.toFavorite(): Favorite{
    return Favorite(
            key = key
    )
}