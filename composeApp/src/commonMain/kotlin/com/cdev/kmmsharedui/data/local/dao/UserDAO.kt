package com.cdev.kmmsharedui.data.local.dao

import com.cdev.kmmsharedui.data.local.entities.UserEntity
import com.cdev.kmmsharedui.data.local.entities.asDomainModel
import com.cdev.kmmsharedui.domain.domain_model.UserDomainModel
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query

abstract class UserDAO {

    abstract suspend fun getUsers(): List<UserDomainModel>
    abstract suspend fun saveUsers(userEntities: List<UserEntity>)
    abstract suspend fun saveUser(userEntity: UserEntity)
}

class UserDAOImpl(private val realm: Realm) : UserDAO() {
    override suspend fun getUsers(): List<UserDomainModel> {
        return realm.query<UserEntity>().find().asDomainModel()
    }

    override suspend fun saveUsers(userEntities: List<UserEntity>) {
        userEntities.forEach {
            saveUser(it)
        }
    }

    override suspend fun saveUser(userEntity: UserEntity) {
        realm.query<UserEntity>("id = $0", userEntity.id).find().ifEmpty {
            realm.writeBlocking {
                this.copyToRealm(
                    userEntity
                )
            }
        }
    }
}