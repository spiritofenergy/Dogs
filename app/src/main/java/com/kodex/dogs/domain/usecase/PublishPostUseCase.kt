package com.kodex.dogs.domain.usecase

import com.kodex.dogs.domain.model.Book
import com.kodex.dogs.domain.repository.MyPostsRepo
import javax.inject.Inject

class PublishPostUseCase @Inject constructor(
    private val repo: MyPostsRepo

    ) {
        suspend operator fun invoke(book: Book): Result<Unit> {
            return try {
                val result = repo.upload(book) // Ваш метод отправки на Firebase
                result // Result<Book> с onSuccess/onFailure
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }