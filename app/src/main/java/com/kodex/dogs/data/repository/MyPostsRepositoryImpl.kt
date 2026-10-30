package com.kodex.dogs.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.kodex.dogs.data.mapper.toBookDTO
import com.kodex.dogs.data.source.remote.FirebaseBooksDataSource
import com.kodex.dogs.domain.model.Book
import com.kodex.dogs.domain.repository.MyPostsRepo
import com.kodex.dogs.domain.repository.UserRoleRepo
import com.kodex.dogs.ui.db.RoomDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

class MyPostsRepositoryImpl @Inject constructor(
    private val dao: RoomDao,
    private val remote: FirebaseBooksDataSource,
    private val auth: FirebaseAuth,
    private val userRoleRepo: UserRoleRepo
) : MyPostsRepo {

    override fun observeMyPosts(uid: String): Flow<List<Book>> =
        dao.observeMyPosts(uid).flowOn(Dispatchers.IO)

    override suspend fun getPost(key: String): Result<Book?> = runCatching {
        withContext(Dispatchers.IO) { dao.getPostByKey(key) }
    }

    override suspend fun saveDraft(book: Book): Result<Book> = runCatching {
        withContext(Dispatchers.IO) {
            val uid = auth.uid ?: throw IllegalStateException("Нужен вход в аккаунт")
            val key = book.key.ifEmpty { UUID.randomUUID().toString() }
            val currentRole = userRoleRepo.observeUserRole(uid).first()
            val saved = book.copy(
                key = key,
                authorUid = uid,
                isAuthor = true,
                authorRole = currentRole.level,
                isUploaded = false,
                searchTitle = book.title.lowercase(),
                timeStamp = System.currentTimeMillis()
            )
            dao.insertPost(saved)
            saved
        }
    }

    override suspend fun upload(book: Book): Result<Unit> = runCatching {
        withContext(Dispatchers.IO) {
            val uid = auth.uid ?: throw IllegalStateException("Нужен вход в аккаунт")
            val currentRole = userRoleRepo.observeUserRole(uid).first()
            val published = book.copy(
                key = book.key.ifEmpty { UUID.randomUUID().toString() },
                authorUid = uid,
                isAuthor = true,
                authorRole = currentRole.level,
                isUploaded = true,
                searchTitle = book.title.lowercase()
            )
            remote.saveBook(published.toBookDTO()).getOrThrow()
            dao.insertPost(published)
        }
    }

    override suspend fun delete(book: Book): Result<Unit> = runCatching {
        withContext(Dispatchers.IO) {
            if (book.isUploaded) remote.deleteBook(book.toBookDTO()).getOrThrow()
            dao.deleteByKey(book.key)
        }
    }
}