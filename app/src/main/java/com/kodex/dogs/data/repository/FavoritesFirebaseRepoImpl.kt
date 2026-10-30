package com.kodex.dogs.data.repository

 import com.kodex.dogs.data.source.remote.FirebaseFavoritesDataSource
import com.kodex.dogs.domain.model.Favorite
import com.kodex.dogs.domain.repository.FavoritesRepo
import javax.inject.Inject

class FavoritesFirebaseRepoImpl @Inject constructor(
    private val dataSource: FirebaseFavoritesDataSource
): FavoritesRepo {
    override suspend fun getIdsFavesList(): Result<List<String>> {
       return dataSource.getIdsFavesList()
    }

    override suspend fun onFavorites(
        favorite: Favorite,
        isFav: Boolean
    ): Result<Unit> {
        return dataSource.onFavorites(favorite, isFav)
    }
}