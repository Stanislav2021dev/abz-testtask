package com.abzagency.features.users.models.presentation

import com.abzagency.features.users.models.domain.UserDomainModel

data class UserPresentationModel(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val position: String,
    val photo: String
)

fun UserDomainModel.toPresentationModel() =
    UserPresentationModel(
        id = id,
        name = name,
        phone = phone,
        email = email,
        photo = photo,
        position = position
    )