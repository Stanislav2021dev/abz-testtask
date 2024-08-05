package com.abzagency.features.signup.models.presentation

import com.abzagency.features.signup.models.domain.PositionDomainModel

data class PositionPresentationModel(
    val id: Int,
    val name: String
)

fun PositionDomainModel.toPresentationModel() =
    PositionPresentationModel(
        id = id,
        name = name
    )