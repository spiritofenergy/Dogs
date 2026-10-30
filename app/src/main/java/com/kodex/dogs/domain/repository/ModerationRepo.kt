package com.kodex.dogs.domain.repository

import com.kodex.dogs.domain.model.RatingData

interface ModerationRepo {
    suspend fun  acceptComment(ratingData: RatingData): Result<Unit>
    suspend fun getCommentsToModerate(): Result <List<RatingData>>
    suspend fun deleteComment(uid: String): Result<Unit>
    
}