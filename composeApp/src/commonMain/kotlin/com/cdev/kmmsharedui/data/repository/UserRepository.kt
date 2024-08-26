package  com.cdev.kmmsharedui.data.repository

import com.cdev.kmmsharedui.data.local.dao.UserDAO
import com.cdev.kmmsharedui.data.remote.dto.UserDTO
import com.cdev.kmmsharedui.data.remote.dto.asDomainModel
import com.cdev.kmmsharedui.data.remote.service.UserKtorService
import com.cdev.kmmsharedui.domain.domain_model.UserDomainModel
import com.cdev.kmmsharedui.domain.domain_model.asDao
import com.cdev.kmmsharedui.domain.util.DomainResult
import com.cdev.kmmsharedui.domain.util.networkBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking


abstract class UserRepository {
    abstract suspend fun getUsers(): Flow<DomainResult<List<UserDomainModel>>>

    abstract suspend fun getUser(
        name: String
    ): UserDTO
}

class UserRepositoryImpl(
    private val ktorService: UserKtorService,
    private val realmService: UserDAO
) : UserRepository() {

    override suspend fun getUsers(): Flow<DomainResult<List<UserDomainModel>>> = networkBoundResource(
        query = {
            runBlocking { flowOf(realmService.getUsers()) }
        },
        fetch = {
            ktorService.getUsers()
        },
        saveFetchResult = { users ->
            realmService.saveUsers(users.asDomainModel().asDao())
        },
        isReceiveDataQuery = true,
    )

    override suspend fun getUser(
        name: String
    ): UserDTO = ktorService.getUser(
        name = name,
    )
}