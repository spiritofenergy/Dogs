package com.kodex.dogs.domain.usecase

import com.kodex.dogs.domain.model.Book
import com.kodex.dogs.domain.repository.SavedPostsRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveSavedPostsUseCase @Inject constructor(
    private val repository: SavedPostsRepo
    ) {
    operator fun invoke(): Flow<List<Book>> {
        return repository.observeSavedPosts()
    }
}
