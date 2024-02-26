package com.felpster.userslist.di

import com.felpster.userslist.data.UserRepositoryImpl
import com.felpster.userslist.data.remote.UserApi
import com.felpster.userslist.domain.repository.UserRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
abstract class UsersModule {
    @Binds
    abstract fun bindsUserRepository(it: UserRepositoryImpl): UserRepository

    companion object {

        @Provides
        fun provideUserApi(): UserApi {
            val moshi =
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()

            return Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(UserApi::class.java)
        }

    }
}
