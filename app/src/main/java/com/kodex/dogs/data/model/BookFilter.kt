package com.kodex.dogs.data.model

import com.kodex.dogs.domain.model.BookCategories
import com.kodex.dogs.domain.model.FilterData

data class BookFilter(
    var category: BookCategories = BookCategories.OTHER,
    var searchText: String = "",
    var filterData: FilterData = FilterData()
)
