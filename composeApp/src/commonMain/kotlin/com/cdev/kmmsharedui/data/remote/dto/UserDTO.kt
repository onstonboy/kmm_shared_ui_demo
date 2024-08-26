package com.cdev.kmmsharedui.data.remote.dto

import com.cdev.kmmsharedui.domain.domain_model.UserDomainModel
import kotlinx.serialization.SerialName


@kotlinx.serialization.Serializable
data class UserDTO(
    @SerialName("id")
    val id: String,
    @SerialName("login")
    val name: String,
)

fun List<UserDTO>.asDomainModel(): List<UserDomainModel> {
    return this.map {
        UserDomainModel(
            id = it.id,
            name = it.name,
        )
    }
}