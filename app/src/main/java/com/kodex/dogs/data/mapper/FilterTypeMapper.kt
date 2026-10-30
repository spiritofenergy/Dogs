package com.kodex.dogs.data.mapper

import com.kodex.dogs.data.source.remote.FirebaseConst
import com.kodex.dogs.domain.model.FilterType

fun FilterType.toFirebaseFields(): String = when(this) {
    FilterType.TITLE -> FirebaseConst.TITLE
    FilterType.PRICE -> FirebaseConst.PRICE
}