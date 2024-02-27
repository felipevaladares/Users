package com.felpster.userslist.data

import com.felpster.userslist.data.remote.UserApi
import com.felpster.userslist.domain.model.toDomain
import com.felpster.userslist.domain.repository.UserRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi,
) : UserRepository {
    override fun getUsers() =
        flow {
            emit(
                userApi.getUsers().map {
                    it.toDomain()
                },
            )
        }
}
