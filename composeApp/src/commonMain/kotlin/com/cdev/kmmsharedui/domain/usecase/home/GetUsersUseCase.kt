package com.cdev.kmmsharedui.domain.usecase.home

import com.cdev.kmmsharedui.data.repository.UserRepository
import kotlinx.coroutines.flow.flow

class GetUsersUseCase(
    private val userRepository: UserRepository,
) {

    operator fun invoke() = flow {
        val response = userRepository.getUsers()

        emit(response)
    }
}