package com.kodex.dogs.data.source.local

import com.kodex.dogs.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface SavedPostsLocalSource {
    fun observePosts(): Flow<List<Book>>

    suspend fun exists(key: String): Boolean

    suspend fun insert(book: Book)

    suspend fun deleteByKey(key: String): Int
}