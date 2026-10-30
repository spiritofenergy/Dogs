package com.kodex.dogs.domain.usecase

import com.kodex.dogs.data.mapper.DraftMapper
import com.kodex.dogs.domain.model.PostEditorState
import com.kodex.dogs.ui.db.DraftDao
 import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class ObserveLatestDraftUseCase @Inject constructor(
    private val draftDao: DraftDao
) {
    operator fun invoke(): Flow<PostEditorState?> =
        draftDao.getLatestDraft().map { it?.let(DraftMapper::toState) }
}