package com.kodex.dogs.domain.tarif

import com.kodex.dogs.domain.model.UserRole
// Результат применения отложенного тарифа после регистрации

sealed interface ApplyRoleResult {
    data object NoDesiredRole : ApplyRoleResult
    data class PaymentRequired(val role: UserRole) : ApplyRoleResult
    data class Updated(val role: UserRole) : ApplyRoleResult
    data class Failed(val message: String) : ApplyRoleResult
}