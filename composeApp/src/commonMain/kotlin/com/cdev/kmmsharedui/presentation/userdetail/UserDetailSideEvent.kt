package com.cdev.kmmsharedui.presentation.userdetail

import com.cdev.kmmsharedui.domain.domain_model.UserDomainModel

sealed class UserDetailSideEvent {
    data class CacheUser(val userDomainModel: UserDomainModel) : UserDetailSideEvent()
}