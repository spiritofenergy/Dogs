package com.kodex.dogs.domain.user

data class AuthUser(
    val uid: String,
    val isAnonymous: Boolean,
    val email: String?
)