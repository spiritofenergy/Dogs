package com.kodex.dogs.domain.usecase

import com.kodex.dogs.data.mapper.DraftMapper
import com.kodex.dogs.domain.model.Book
import com.kodex.dogs.domain.model.PostEditorState
import com.kodex.dogs.domain.repository.MyPostsRepo
import com.kodex.dogs.ui.db.DraftDao
import javax.inject.Inject

class SaveDraftUseCase @Inject constructor(
    private val repo: MyPostsRepo,
    private val draftDao: DraftDao
) {
    suspend operator fun invoke(state: PostEditorState): Long {
        // Сохраняем только если есть хоть какие-то данные
        if (state.title.isBlank() && state.description.isBlank()) return -1L

        val entity = DraftMapper.toEntity(state)
        return draftDao.saveDraft(entity)
    }

    suspend operator fun invoke(book: Book): Result<Book> = repo.saveDraft(book)
}