package com.felpster.userslist.commons

import com.felpster.userslist.domain.model.User
import com.felpster.userslist.domain.repository.UserRepository
import java.io.IOException
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.onEach

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
        val fakeUser = User(1, "name", "username", "email", "site")
        val fakeUser2 = User(2, "name 2", "username 2", "email 2", "site 2")
        val users = listOf(fakeUser, fakeUser2)
    }
}