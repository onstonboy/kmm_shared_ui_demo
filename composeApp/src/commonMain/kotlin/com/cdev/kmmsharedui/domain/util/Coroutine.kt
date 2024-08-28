package com.cdev.kmmsharedui.domain.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

interface CoroutineProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}

object CoroutineProviderImpl : CoroutineProvider {
    override val main = Dispatchers.Main
    override val io = Dispatchers.IO
    override val default = Dispatchers.Default
    override val unconfined = Dispatchers.Unconfined
}

class TestCoroutineProvider(testDispatcher: CoroutineDispatcher) : CoroutineProvider {
    override val main = testDispatcher
    override val io = testDispatcher
    override val default = testDispatcher
    override val unconfined = testDispatcher
}