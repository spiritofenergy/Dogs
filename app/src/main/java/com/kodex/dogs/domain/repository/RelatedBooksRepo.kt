package com.kodex.dogs.domain.repository

import com.kodex.dogs.domain.model.Book
import com.kodex.dogs.domain.model.BookCategories

interface RelatedBooksRepo {
    suspend fun getRelatedBooks(category: BookCategories, excludeKey: String): Result<List<Book>>
}