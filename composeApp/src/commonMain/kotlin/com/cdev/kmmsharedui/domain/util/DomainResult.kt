package com.cdev.kmmsharedui.domain.util

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface DomainResult<out T> {
    data class Success<T>(val data: T) : DomainResult<T>
    data class Error(val exception: CustomMessage) : DomainResult<Nothing>
    data object Loading : DomainResult<Nothing>
    data object Idle : DomainResult<Nothing>
}

fun <T> Flow<T>.asResult(): Flow<DomainResult<T>> {
    return this
        .map<T, DomainResult<T>> {
            DomainResult.Success(it)
        }
        .onStart { emit(DomainResult.Loading) }
        .catch {
            it.printStackTrace()
            emit(DomainResult.Error((it as Exception).getRealException()))
        }
}

sealed class CustomMessage(val message: String = "") {


    data object NetworkError : CustomMessage("Something wrong with network, please try again.")
    data object RandomError : CustomMessage("Something went wrong, please try again.")
    data object ResponseError :
        CustomMessage("We are fixing your problem, Thank you for your patience.")

    data object NoInternet : CustomMessage("No Internet")
    data object NotFound : CustomMessage("Not Found")
    data class ExceptionMessage(val error: String) : CustomMessage(message = error)

}

fun Exception.getRealException(): CustomMessage = when (this) {
    is HttpRequestTimeoutException -> {

        CustomMessage.NetworkError
    }

    is RedirectResponseException -> {
        CustomMessage.NetworkError
    }

    is ClientRequestException -> {
        CustomMessage.NetworkError
    }

    is ServerResponseException -> {
        CustomMessage.ResponseError
    }

    else -> {
        CustomMessage.RandomError
    }
}
