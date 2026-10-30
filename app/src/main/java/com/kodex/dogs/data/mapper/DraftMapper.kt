package com.kodex.dogs.data.mapper

import com.kodex.dogs.data.source.local.DraftEntity
import com.kodex.dogs.domain.model.PostEditorState
import com.kodex.dogs.domain.model.BookCategories

object DraftMapper {
    fun toEntity(state: PostEditorState): DraftEntity = DraftEntity(
        title = state.title.takeIf { it.isNotBlank() },
        description = state.description.takeIf { it.isNotBlank() },
        price = state.price.toIntOrNull(),
        telephone = state.telephone.takeIf { it.isNotBlank() },
        village = state.village.takeIf { it.isNotBlank() },
        street = state.street.takeIf { it.isNotBlank() },
        house = state.house.takeIf { it.isNotBlank() },
        flat = state.flat.takeIf { it.isNotBlank() },
        categoryId = state.category.id,
        delivery = state.delivery,
        payment = state.payment,
        location = state.location,
        imageUrl = state.existingImageUrl.takeIf { it.isNotBlank() }
    )

    fun toState(entity: DraftEntity): PostEditorState = PostEditorState(
        title = entity.title ?: "",
        description = entity.description ?: "",
        price = entity.price?.toString() ?: "",
        telephone = entity.telephone ?: "",
        village = entity.village ?: "",
        street = entity.street ?: "",
        house = entity.house ?: "",
        flat = entity.flat ?: "",
        category = BookCategories.entries.find { it.id == entity.categoryId } ?: BookCategories.SERVICES,
        delivery = entity.delivery,
        payment = entity.payment,
        location = entity.location,
        existingImageUrl = entity.imageUrl ?: ""
    )
}