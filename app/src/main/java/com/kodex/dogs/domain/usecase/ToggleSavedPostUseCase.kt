package com.kodex.dogs.domain.usecase

import com.kodex.dogs.domain.model.Book
import com.kodex.dogs.domain.repository.SavedPostsRepo
import javax.inject.Inject

class ToggleSavedPostUseCase @Inject constructor(
    private val repository: SavedPostsRepo
) {

    suspend operator fun invoke(book: Book): Boolean {
        return repository.toggle(book)
    }
}