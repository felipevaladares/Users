package com.felpster.userslist.domain.repository

import com.felpster.userslist.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<List<User>>
}
