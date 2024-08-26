package com.cdev.kmmsharedui.presentation.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.cdev.kmmsharedui.domain.usecase.home.GetUsersUseCase
import com.cdev.kmmsharedui.domain.util.DomainResult
import com.cdev.kmmsharedui.domain.util.asResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenModel(
    private val getUsersUseCase: GetUsersUseCase,
) : ScreenModel {

    private val _state = MutableStateFlow<HomeScreenState>(HomeScreenState.Idle)
    var state = _state.asStateFlow()

    fun onIntent(intent: HomeScreenSideEvent) {

        when (intent) {
            is HomeScreenSideEvent.GetUsers -> {
                getUsers()
            }
        }
    }

    private fun getUsers() {
        screenModelScope.launch {
            doGetUsersUseCase()
        }
    }

    private suspend fun doGetUsersUseCase() {
        getUsersUseCase().asResult().collectLatest { result ->
            when (result) {
                is DomainResult.Error -> {
                    _state.update {
                        HomeScreenState.Error(result.exception.message)
                    }
                }

                DomainResult.Idle -> {
                    _state.update {
                        HomeScreenState.Idle
                    }
                }

                DomainResult.Loading -> {
                    _state.update {
                        HomeScreenState.Loading
                    }
                }

                is DomainResult.Success -> {
                    result.data.collectLatest { result2 ->
                        when (result2) {
                            is DomainResult.Success -> {
                                _state.update {
                                    HomeScreenState.Success(result2.data)
                                }
                            }

                            DomainResult.Loading -> {
                                _state.update {
                                    HomeScreenState.Loading
                                }
                            }

                            else -> {}
                        }
                    }
                }
            }
        }
    }
}
