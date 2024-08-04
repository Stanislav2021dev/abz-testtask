package com.abzagency.features.users.models.remote

import com.abzagency.features.users.models.domain.PositionDomainModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PositionsWrapperRemoteModel(
    @SerialName("positions")
    val positions: List<PositionRemoteModel>
)