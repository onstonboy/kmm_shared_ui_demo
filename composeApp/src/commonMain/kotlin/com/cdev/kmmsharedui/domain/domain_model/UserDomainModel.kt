package com.cdev.kmmsharedui.domain.domain_model

import com.cdev.kmmsharedui.data.local.entities.UserEntity

data class UserDomainModel(
    val id: String,
    val name: String,
)

fun UserDomainModel.asDao(): UserEntity {
    return UserEntity().also {
        it.id = this.id
        it.name = this.name
    }
}

fun List<UserDomainModel>.asDao() = map {
    it.asDao()
}
