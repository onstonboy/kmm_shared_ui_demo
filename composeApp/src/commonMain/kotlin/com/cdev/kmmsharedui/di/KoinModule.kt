package com.cdev.kmmsharedui.di

import com.cdev.kmmsharedui.data.local.dao.UserDAO
import com.cdev.kmmsharedui.data.local.dao.UserDAOImpl
import com.cdev.kmmsharedui.data.local.entities.UserEntity
import com.cdev.kmmsharedui.data.local.shared_preference.MySharedPref
import com.cdev.kmmsharedui.data.remote.service.UserKtorService
import com.cdev.kmmsharedui.data.remote.service.UserKtorServiceImpl
import com.cdev.kmmsharedui.data.repository.UserRepository
import com.cdev.kmmsharedui.data.repository.UserRepositoryImpl
import com.cdev.kmmsharedui.domain.usecase.home.GetUsersUseCase
import com.cdev.kmmsharedui.domain.util.CoroutineProvider
import com.cdev.kmmsharedui.domain.util.CoroutineProviderImpl
import com.cdev.kmmsharedui.presentation.home.HomeScreenModel
import com.cdev.kmmsharedui.presentation.userdetail.UserDetailScreenModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(
    enableNetworkLogs: Boolean = false,
    baseUrl: String,
    mySharedPref: MySharedPref,
    appDeclaration: KoinAppDeclaration = {}
) {
    startKoin {
        appDeclaration()
        modules(commonModule(enableNetworkLogs = enableNetworkLogs, baseUrl, mySharedPref))
    }
}

fun initKoin(baseUrl: String, mySharedPref: MySharedPref) {
    initKoin(enableNetworkLogs = true, baseUrl, mySharedPref) {}
}

fun commonModule(enableNetworkLogs: Boolean, baseUrl: String, mySharedPref: MySharedPref) =
    getDataModule(
        enableNetworkLogs,
        baseUrl,
        mySharedPref
    ) + getUseCaseModule() + getHelperModule() + screenModelsModule

fun getHelperModule() = module {
    single<CoroutineProvider> {
        CoroutineProviderImpl
    }
}

fun getDataModule(enableNetworkLogs: Boolean, baseUrl: String, mySharedPref: MySharedPref) = module {
    single {
        createHttpClient(enableNetworkLogs, baseUrl)
    }

    single {
        Realm.open(
            RealmConfiguration
                .Builder(schema = setOf(UserEntity::class))
                .build()
        )
    }

    single<UserDAO> {
        UserDAOImpl(
            get()
        )
    }

    single<UserKtorService> {
        UserKtorServiceImpl(
            get()
        )
    }

    single<UserRepository> {
        UserRepositoryImpl(
            get(),
            get(),
            get()
        )
    }
}

@OptIn(ExperimentalSerializationApi::class)
fun createHttpClient(enableNetworkLogs: Boolean, baseUrl: String) = HttpClient {
    install(HttpTimeout) {
        socketTimeoutMillis = 60_000
        requestTimeoutMillis = 60_000
    }

    //Logging plugin combined with kermit(KMP Logger library)
    if (enableNetworkLogs) {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    println("KtorClient $message")
                }
            }
        }
    }

    defaultRequest {
        url(baseUrl)
    }

    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = false
            },
            contentType = ContentType.Any
        )
    }
}

var screenModelsModule = module {
    factoryOf(::HomeScreenModel)
    factoryOf(::UserDetailScreenModel)
}

fun getUseCaseModule() = module {
    single {
        GetUsersUseCase(get())
    }
}

