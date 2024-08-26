package com.cdev.kmmsharedui.presentation.home

import com.cdev.kmmsharedui.domain.domain_model.UserDomainModel

sealed interface HomeScreenState{

    data object Loading: HomeScreenState

    data object Idle : HomeScreenState

    data class Success(val users: List<UserDomainModel>) : HomeScreenState

    data class Error(val errorMessage: String) : HomeScreenState
}

