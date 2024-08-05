package com.abzagency.features.signup.models.remote

import com.abzagency.features.signup.models.domain.SignUpDomainModel
import java.io.File

data class SignupRemoteModel(
    val name: String,
    val email: String,
    val phone: String,
    val positionId: Int,
    val photo: File
)

fun SignUpDomainModel.toRemoteModel() =
    SignupRemoteModel(
        name = name,
        email = email,
        phone = phone,
        positionId = positionId,
        photo = photo
    )