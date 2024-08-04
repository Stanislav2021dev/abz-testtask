package com.abzagency.features.users.models.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserTokenRemoteModel(
    @SerialName("token")
    val token: String
)