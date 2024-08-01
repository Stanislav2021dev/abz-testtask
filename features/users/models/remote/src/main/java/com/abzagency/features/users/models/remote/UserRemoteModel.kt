package com.abzagency.features.users.models.remote

import com.abzagency.features.users.models.domain.UserDomainModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRemoteModel(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("email")
    val email: String,
    @SerialName("phone")
    val phone: String,
    @SerialName("position")
    val position: String,
    @SerialName("position_id")
    val positionId: Int,
    @SerialName("registration_timestamp")
    val registrationTimeStamp: Long,
    @SerialName("photo")
    val photo: String
)

fun UserRemoteModel.toDomainModel() =
    UserDomainModel(
        id = id,
        name = name,
        phone = phone,
        email = email,
        photo = photo,
        position = position
    )