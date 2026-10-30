package com.kodex.dogs.domain.usecase

import com.kodex.dogs.domain.model.Book
import com.kodex.dogs.domain.repository.MyPostsRepo
import javax.inject.Inject

class GetMyPostUseCase @Inject constructor(private val repo: MyPostsRepo) {
    suspend operator fun invoke(key: String): Result<Book?> = repo.getPost(key)
}