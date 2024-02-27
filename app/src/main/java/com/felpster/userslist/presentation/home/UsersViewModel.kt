package com.felpster.userslist.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felpster.userslist.commons.extensions.Result
import com.felpster.userslist.commons.extensions.asResult
import com.felpster.userslist.domain.model.User
import com.felpster.userslist.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn

sealed class UsersViewState {
    data class Success(val users: List<User>) : UsersViewState()

    data class Error(val message: String?) : UsersViewState()

    data class Loading(val message: String? = null) : UsersViewState()
}

sealed class UsersViewEvent {
    data class OnCardClick(val user: User) : UsersViewEvent()
}

sealed class NavigationEvent {
    data class NavigateToUserDetails(val user: User) : NavigationEvent()
}

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    private val navigationEventsChannel = Channel<NavigationEvent>(Channel.UNLIMITED)
    val navigationEvents = navigationEventsChannel.receiveAsFlow()

    val state: StateFlow<UsersViewState> by lazy {
        userRepository
            .getUsers()
            .asResult()
            .map { result ->
                when (result) {
                    is Result.Loading -> UsersViewState.Loading(null)
                    is Result.Success -> UsersViewState.Success(result.data)
                    is Result.Error -> UsersViewState.Error(result.exception?.message)
                }
            }
            .stateIn(
                scope = viewModelScope,
                initialValue = UsersViewState.Loading(null),
                started = SharingStarted.WhileSubscribed(SUBSCRIPTION_TIMEOUT),
            )
    }


    fun onEvent(usersViewEvent: UsersViewEvent) {
        when (usersViewEvent) {
            is UsersViewEvent.OnCardClick ->
                navigationEventsChannel.trySend(
                    NavigationEvent.NavigateToUserDetails(usersViewEvent.user),
                )
        }
    }

    private companion object {
        private const val SUBSCRIPTION_TIMEOUT = 5_000L
    }
}


