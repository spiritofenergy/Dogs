package com.kodex.dogs.presentation.myPosts


import com.kodex.dogs.domain.model.PostEditorState

/**
 * События, которые ViewModel отправляет на экран (UI)
 */
sealed interface MyPostsEvent {

    data class Toast(val message: String) : MyPostsEvent

    data class RestoreDraft(val state: PostEditorState) : MyPostsEvent
    object NavigateBack : MyPostsEvent
}