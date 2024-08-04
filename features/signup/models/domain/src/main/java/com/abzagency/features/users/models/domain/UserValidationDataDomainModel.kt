package com.abzagency.features.users.models.domain

data class UserValidationDataDomainModel(
    val isValid: Boolean,
    val errorMessage: String?
)