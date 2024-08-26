package com.cdev.kmmsharedui.presentation.userdetail

import cafe.adriel.voyager.core.model.ScreenModel
import com.cdev.kmmsharedui.domain.domain_model.UserDomainModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UserDetailScreenModel : ScreenModel {

    private val _state = MutableStateFlow<UserDetailState>(UserDetailState.Idle)
    var state = _state.asStateFlow()

    private var userDomainModel: UserDomainModel? = null

    fun onIntent(intent: UserDetailSideEvent) {

        when (intent) {
            is UserDetailSideEvent.CacheUser -> {
                userDomainModel = intent.userDomainModel
                _state.update {
                    UserDetailState.Success(userDomainModel!!)
                }
            }
        }
    }
}
