package com.cdev.kmmsharedui.domain.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true },
    isReceiveDataQuery: Boolean = false,
) = flow {
    val data = query().firstOrNull()
    emit(DomainResult.Loading)
    if (data != null && isReceiveDataQuery) {
        emit(DomainResult.Success(data))
    }
    val flow = data?.let {
        if (shouldFetch(data)) {
            try {
                saveFetchResult(fetch())
                query().map { DomainResult.Success(it) }
            } catch (throwable: Throwable) {
                query().map { DomainResult.Error(CustomMessage.ExceptionMessage(throwable.message ?: "")) }
            }
        } else {
            query().map { DomainResult.Success(it) }
        }
    } ?: kotlin.run {
        saveFetchResult(fetch())
        query().map { DomainResult.Success(it) }
    }
    emitAll(flow)
}