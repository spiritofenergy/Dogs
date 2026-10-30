package com.kodex.dogs.domain.usecase

import com.kodex.dogs.domain.model.Book
import com.kodex.dogs.domain.model.BookCategories
import com.kodex.dogs.domain.repository.RelatedBooksRepo
import javax.inject.Inject

class GetRelatedBooksUseCase @Inject constructor(
    private val repository: RelatedBooksRepo
) {
    suspend operator fun invoke(
        category: BookCategories,
        excludeKey: String,
        limit: Int = 10
    ): Result<List<Book>> =
        repository.getRelatedBooks(category, excludeKey)
            .map { list -> list.take(limit) }
}