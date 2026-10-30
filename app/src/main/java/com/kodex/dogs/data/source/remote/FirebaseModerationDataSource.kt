package com.kodex.dogs.data.source.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kodex.dogs.data.source.remote.FirebaseConst.MODERATION
import com.kodex.dogs.data.source.remote.FirebaseConst.RATING
import com.kodex.dogs.data.source.remote.FirebaseConst.RATING_DATA
import com.kodex.dogs.data.model.RatingDataDTO
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

/**
 * DataSource модерации комментариев.
 * Только сырые операции Firestore — без бизнес-логики.
 */
@Singleton
class FirebaseModerationDataSource @Inject constructor(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth
) {

    /** Все комментарии, ожидающие модерации */
    suspend fun getAllCommentsToModerate(): Result<List<RatingDataDTO>> {
        return try {
            val snapshot = db.collection(MODERATION)
                .get()
                .await()

            val comments = snapshot.documents.mapNotNull { document ->
                document.toObject(RatingDataDTO::class.java)
                    ?.copy(uid = document.id)
            }
            Result.success(comments)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /** Одобрить комментарий */
    suspend fun acceptComment(ratingDataDTO: RatingDataDTO): Result<Unit> {
        if (ratingDataDTO.uid.isBlank() || ratingDataDTO.bookId.isBlank()) {
            return Result.failure(
                IllegalArgumentException("Для публикации отзыва нужны UID автора и ID объявления")
            )
        }

        return try {
            val pendingComment = db.collection(MODERATION).document(ratingDataDTO.uid)
            val publishedComment = db.collection(RATING)
                .document(ratingDataDTO.bookId)
                .collection(RATING_DATA)
                .document(ratingDataDTO.uid)

            db.batch()
                .set(publishedComment, ratingDataDTO)
                .delete(pendingComment)
                .commit()
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /** Отклонить / удалить комментарий */
    suspend fun deleteComment(uid: String): Result<Unit> {
        return try {
            db.collection(MODERATION)
                .document(uid)
                .delete()
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}