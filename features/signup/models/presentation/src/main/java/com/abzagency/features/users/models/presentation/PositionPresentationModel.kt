package com.abzagency.features.users.models.presentation

import com.abzagency.features.users.models.domain.PositionDomainModel

data class PositionPresentationModel(
    val id: Int,
    val name: String
)

fun PositionDomainModel.toPresentationModel() =
    PositionPresentationModel(
        id = id,
        name = name
    )