package com.abzagency.features.signup.models.domain

data class UserValidationDataDomainModel(
    val isValid: Boolean,
    val errorMessage: String?
)