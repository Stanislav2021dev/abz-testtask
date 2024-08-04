package com.abzagency.features.users.models.remote

import com.abzagency.features.users.models.domain.PositionDomainModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PositionRemoteModel(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)

fun PositionRemoteModel.toDomainModel() =
    PositionDomainModel(
        id = id,
        name = name
    )