package com.abzagency.features.signup.models.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserTokenRemoteModel(
    @SerialName("token")
    val token: String
)