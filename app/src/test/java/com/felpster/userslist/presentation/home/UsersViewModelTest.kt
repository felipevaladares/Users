package com.felpster.userslist.presentation.home

import app.cash.turbine.test
import com.felpster.userslist.commons.FakeUserRepository
import com.felpster.userslist.commons.MainDispatcherRule
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
    fun `Initial state should be loading`() = runTest {
        //using flow turbine to help with the tests
        viewModel.state.test {
            val firstItem = awaitItem()

            //using Google Truth library for assertions, more fluent, much better logs
            assertThat(firstItem).isEqualTo(UsersViewState.Loading(null))
        }
    }

    @Test
    fun `state should be Success after loading users`() = runTest {
        viewModel.state.test {
            val firstItem = awaitItem()
            assertThat(firstItem).isEqualTo(UsersViewState.Loading(null))

            repository.emitUsers(FakeUserRepository.fakeUsersList)

            val secondItem = awaitItem()
            assertThat(secondItem).isEqualTo(UsersViewState.Success(FakeUserRepository.fakeUsersList))
        }
    }

    @Test
    fun `state should be Error if loading users fails`() = runTest {
        viewModel.state.test {
            val firstItem = awaitItem()
            assertThat(firstItem).isEqualTo(UsersViewState.Loading(null))

            repository.emitUsers(FakeUserRepository.fakeUsersList, true)

            val secondItem = awaitItem()
            assertThat(secondItem).isEqualTo(UsersViewState.Error(null))
        }
    }

    @Test
    fun `OnCardClick should send NavigationEvent NavigateToUserDetails for the given user`() = runTest {
        viewModel.navigationEvents.test {
            val user = mockk<User>()

            viewModel.onEvent(UsersViewEvent.OnCardClick(user))

            assertThat(expectMostRecentItem()).isEqualTo(NavigationEvent.NavigateToUserDetails(user))
        }
    }
}