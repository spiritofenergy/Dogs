package com.kodex.dogs.presentation.detailScreen.states

import com.kodex.dogs.domain.model.RatingData

data class DetailsUiState(
    val isLoading: Boolean = false,
    val comments: List<RatingData> = emptyList(),
    val ratingData: RatingData = RatingData(),
    val error: String? = null,

    val showRateDialog: Boolean = false,
    val ratingDataToShow: RatingData = RatingData(),
    val showCommentDialog: Boolean = false,


    )