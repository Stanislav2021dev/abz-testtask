package com.abzagency.features.users.models.domain

import java.io.File

data class SignUpDomainModel(
    val name: String,
    val email: String,
    val positionId: Int,
    val phone: String,
    val photo: File
)