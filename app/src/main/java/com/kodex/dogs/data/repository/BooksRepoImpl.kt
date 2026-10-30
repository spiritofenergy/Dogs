package com.kodex.dogs.data.repository

import android.net.Uri
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kodex.dogs.data.images.BitmapEncoder
import com.kodex.dogs.data.mapper.toBookDTO
import com.kodex.dogs.data.mapper.toDTO
import com.kodex.dogs.data.mapper.toDomain
import com.kodex.dogs.data.mapper.toRatingData
import com.kodex.dogs.data.source.remote.FirebaseBooksDataSource
import com.kodex.dogs.domain.model.Book
import com.kodex.dogs.domain.model.BookFilterState
import com.kodex.dogs.domain.model.RatingData
import com.kodex.dogs.domain.repository.BooksRepo
import com.kodex.dogs.presentation.home.BookFactoryPaging
import com.kodex.dogs.ui.db.RoomDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

const val BASE_64 = true
class BooksRepo_Impl @Inject constructor(
    private val dataSource: FirebaseBooksDataSource,
    private val dao: RoomDao,
    //private val storageDataSource: FireBaseStorageDataSource,
    private val bitmapEncoder: BitmapEncoder,
    ): BooksRepo{
    override fun getBooks(favsKeysList: List<String>, bookFilter: BookFilterState): Flow<PagingData<Book>> {
        return Pager(
            config = PagingConfig(
                pageSize = 16,
                prefetchDistance = 3,
                initialLoadSize = 26,
            ),  
            pagingSourceFactory = { BookFactoryPaging(
                dataSource,
                favsKeysList,
                bookFilter,
                )
            }
        ).flow
    }

    override suspend fun deleteBook(book: Book): Result<Unit> {
        return dataSource.deleteBook(book.toBookDTO())
    }
        // only BASE64 image
    override suspend fun saveBook(book: Book, uri: Uri?): Result<Unit> {
        val imageUrl = bitmapEncoder.imageToBase64(uri)
        return dataSource.saveBook(book.copy(imageUrl = imageUrl).toBookDTO())
    }
    // Поиск в локальной базе (Room)
    suspend fun getBookFromLocal(key: String): Book? {
        return dao.getPostByKey(key)
    }
    // Поиск в Firebase
    suspend fun getBookFromRemote(key: String): Result<Book?> {
        return try {
            val bookDto = dataSource.getBookByKey(key) // Нужно добавить этот метод в FirebaseBooksDataSource
            Result.success(bookDto?.toDomain()) // Конвертация DTO в доменную модель
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    override suspend fun submitUserRating(
        ratingData: RatingData,
        bookId: String
    ): Result<Unit> {
        return dataSource.submitUserRating(ratingData.toDTO(), bookId)
    }

    override suspend fun deleteComment(uid: String): Result<Unit> {
        return dataSource.deleteComment(uid)
    }

    override suspend fun getBookComments(bookId: String): Result<List<RatingData>> {
        return dataSource.getBookComments(bookId).map { list->
            list.map {
                rData -> rData.toRatingData()
            }
        }
    }

    override suspend fun getUserRating(bookId: String): Result<RatingData?> {
        return dataSource.getUserRating(bookId).map {
            it?.toRatingData()
        }
    }
}