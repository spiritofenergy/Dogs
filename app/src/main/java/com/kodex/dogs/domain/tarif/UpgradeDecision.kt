package com.kodex.dogs.domain.tarif

import com.kodex.dogs.domain.model.UserRole
// Что нужно сделать UI после запроса повышения

sealed interface UpgradeDecision {
    data object MaxRole : UpgradeDecision
    data class AuthRequired(val role: UserRole) : UpgradeDecision
    data class PaymentRequired(val role: UserRole) : UpgradeDecision
}