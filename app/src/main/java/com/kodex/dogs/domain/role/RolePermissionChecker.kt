package com.kodex.dogs.domain.role

import com.kodex.dogs.domain.model.Permission
import com.kodex.dogs.domain.model.UserRole

interface RolePermissionChecker {
    fun hasPermission(
        role: UserRole,
        permission: Permission
    ): Boolean

    fun canAccess(
        role: UserRole,
        requiredRole: UserRole
    ): Boolean

    fun canCreatePost(role: UserRole): Boolean

    fun canModerate(role: UserRole): Boolean
}