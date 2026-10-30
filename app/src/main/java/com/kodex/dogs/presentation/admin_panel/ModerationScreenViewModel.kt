package com.kodex.dogs.presentation.admin_panel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodex.dogs.domain.model.RatingData
import com.kodex.dogs.domain.repository.ModerationRepo

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModerationScreenViewModel @Inject constructor(
    private val moderationRepo: ModerationRepo
) : ViewModel(){

    val  commentState  = mutableStateOf(emptyList<RatingData>())
    val errorMessage = mutableStateOf<String?>(null)

    fun acceptComment(ratingData: RatingData) = viewModelScope.launch(){
       val result = moderationRepo.acceptComment(ratingData)
        result.fold(
            onSuccess = {
                errorMessage.value = null
                commentState.value = commentState.value.filter { it.uid != ratingData.uid }
                Log.d("MyLog", "Accept success:  ")
            },
            onFailure = {
                errorMessage.value = "Не удалось опубликовать отзыв: ${it.message}"
                Log.d("MyLog", "Accept error: ${it.message}")
            }
        )
    }
    fun deleteComment(uid: String) = viewModelScope.launch{
        val result = moderationRepo.deleteComment(uid)
        result.fold(
            onSuccess = {
                errorMessage.value = null
                commentState.value = commentState.value.filter { it.uid != uid }
            },
            onFailure = {
                errorMessage.value = "Не удалось удалить отзыв: ${it.message}"
            }
        )

    }
    fun getAllComments () = viewModelScope.launch{
        val result = moderationRepo.getCommentsToModerate()
        result.fold(
            onSuccess = { commentLost ->
                errorMessage.value = null
                commentState.value = commentLost
            },
            onFailure = {
                errorMessage.value = "Не удалось загрузить отзывы: ${it.message}"
            }
        )
    }

    fun clearError() {
        errorMessage.value = null
    }
}