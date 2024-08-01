package com.abzagency.features.users.models.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserWrapperRemoteModel(
    @SerialName("users")
    val users: List<UserRemoteModel>
)