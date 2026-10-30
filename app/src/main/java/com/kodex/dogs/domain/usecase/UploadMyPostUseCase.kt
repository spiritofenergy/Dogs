package com.kodex.dogs.domain.usecase

import com.kodex.dogs.domain.model.Book
import com.kodex.dogs.domain.repository.MyPostsRepo
import javax.inject.Inject

class UploadMyPostUseCase @Inject constructor(
    private val repo: MyPostsRepo
) {
    suspend operator fun invoke(book: Book): Result<Unit> = repo.upload(book)
}