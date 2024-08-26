package com.cdev.kmmsharedui.data.local.entities

import com.cdev.kmmsharedui.domain.domain_model.UserDomainModel
import io.realm.kotlin.types.RealmObject

open class UserEntity : RealmObject {
    var id: String = ""
    var name: String = ""
}

fun UserEntity.asDomainModel() = UserDomainModel(
    id,
    name = name,
)

fun List<UserEntity>.asDomainModel() = map {
    it.asDomainModel()
}
