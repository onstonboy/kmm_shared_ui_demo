package com.cdev.kmmsharedui.presentation.userdetail

import com.cdev.kmmsharedui.domain.domain_model.UserDomainModel

sealed interface UserDetailState{

    data object Loading: UserDetailState

    data object Idle : UserDetailState

    data class Success(val user: UserDomainModel) : UserDetailState

    data class Error(val errorMessage: String) : UserDetailState
}

