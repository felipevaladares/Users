package com.felpster.userslist

import com.felpster.userslist.domain.model.User
import com.felpster.userslist.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeUserRepository: UserRepository {
    override suspend fun getUsers(): Flow<List<User>> {
        return flowOf(listOf(User(1, "name", "username", "email", "site")))
    }
}