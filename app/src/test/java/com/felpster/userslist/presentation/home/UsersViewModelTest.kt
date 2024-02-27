package com.felpster.userslist.presentation.home

import app.cash.turbine.test
import com.felpster.userslist.FakeUserRepository
import com.felpster.userslist.MainDispatcherRule
import com.felpster.userslist.domain.model.User
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UsersViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val repository = FakeUserRepository()
    private val viewModel =  UsersViewModel(repository)

    @Test
    fun `OnCardClick should send NavigationEvent NavigateToUserDetails for the given user`() = runTest {
        val user = mockk<User>()

        viewModel.navigationEvents.test {
            viewModel.onEvent(UsersViewEvent.OnCardClick(user))

            assertThat(expectMostRecentItem()).isEqualTo(NavigationEvent.NavigateToUserDetails(user))
        }
    }
}