package com.kodex.dogs.domain.repository

import com.kodex.dogs.domain.model.Favorite

interface FavoritesRepo {
    suspend fun getIdsFavesList(): Result<List<String>>
    suspend fun onFavorites(favorite: Favorite, isFav: Boolean) : Result<Unit>
}