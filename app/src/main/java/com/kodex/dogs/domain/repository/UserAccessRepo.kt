package com.kodex.dogs.domain.repository

interface UserAccessRepo {
    suspend fun isAdmin(uid: String): Boolean
    suspend fun isRegistered(uid: String): Boolean
}