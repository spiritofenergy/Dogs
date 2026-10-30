package com.kodex.dogs.presentation.home

sealed interface SavedUiEvent {
    data class ShowToast(val message: String) : SavedUiEvent

}