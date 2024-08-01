package com.abzagency.features.users.models.domain

data class UserDomainModel(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val position: String,
    val photo: String
)