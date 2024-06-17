package com.felpster.userslist.commons

import com.felpster.userslist.domain.model.User
import com.felpster.userslist.domain.repository.UserRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.onEach
import java.io.IOException

class FakeUserRepository: UserRepository {
    /**
     * The backing hot flow for the list of users for testing.
     */
    private val usersFlow =
        MutableSharedFlow<List<User>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private var emitError = false
    override fun getUsers(): Flow<List<User>> {
        return usersFlow.onEach {
            if (emitError) {
                throw IOException()
            }
        }
    }

    /**
     * A test-only API to allow controlling the list of users from tests.
     */
    suspend fun emitUsers(users: List<User>, forceError: Boolean = false) {
        emitError = forceError
        usersFlow.emit(users)
    }

    companion object {
        val usersList = listOf(
            User(
                id = 0,
                name = "Felipe",
                username = "username",
                email = "felipe.valadares2@gmail.com",
                website = "http://www.google.com",
            ),
            User(
                id = 0,
                name = "John",
                username = "username",
                email = "john@gmail.com",
                website = "http://www.google.com",
            ),
        )
    }
}