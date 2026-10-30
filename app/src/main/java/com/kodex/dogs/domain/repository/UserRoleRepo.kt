package com.kodex.dogs.domain.repository

import com.kodex.dogs.domain.model.UserRole
import kotlinx.coroutines.flow.Flow

interface UserRoleRepo {
    fun observeUserRole(uid: String): Flow<UserRole>
    suspend fun updateUserRole(uid: String, role: UserRole): Result<Unit>
}