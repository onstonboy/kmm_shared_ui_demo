package  com.cdev.kmmsharedui.data.remote.service

import com.cdev.kmmsharedui.data.remote.dto.UserDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

abstract class UserKtorService {
    abstract suspend fun getUsers(): List<UserDTO>

    abstract suspend fun getUser(name: String): UserDTO
}

class UserKtorServiceImpl(
    private val httpClient: HttpClient
) : UserKtorService() {

    override suspend fun getUsers()
            : List<UserDTO> = httpClient.get(EndPoints.USERS) {}.body<List<UserDTO>>()

    override suspend fun getUser(name: String)
            : UserDTO = httpClient.get(EndPoints.USERS + "/$name") {
        // Set parameter if any
        // parameter("name", name)
    }.body<UserDTO>()

}

