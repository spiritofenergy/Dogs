package com.kodex.dogs.domain.model

data class FilterData(
    val minPrise: Int = 0,
    val maxPrise: Int = 0,
    val filterType: FilterType = FilterType.TITLE

)