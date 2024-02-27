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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

sealed class UsersViewState {
    data class Success(val users: List<User>) : UsersViewState()

    data class Error(val message: String?) : UsersViewState()

    data class Loading(val message: String?) : UsersViewState()
}

sealed class UsersViewEvent {
    data class OnCardClick(val user: User) : UsersViewEvent()
}

sealed class NavigationEvent {
    data class NavigateToUserDetails(val user: User) : NavigationEvent()
}

@HiltViewModel
class UsersViewModel
    @Inject
    constructor(
        private val userRepository: UserRepository,
    ) : ViewModel() {
        private val _state = MutableStateFlow<UsersViewState>(UsersViewState.Loading(null))
        val state: StateFlow<UsersViewState> = _state

        private val navigationEventsChannel = Channel<NavigationEvent>(Channel.UNLIMITED)
        val navigationEvents = navigationEventsChannel.receiveAsFlow()

        init {
            viewModelScope.launch {
                userRepository.getUsers().asResult().collect { result ->
                    when (result) {
                        is Result.Loading -> _state.value = UsersViewState.Loading(null)
                        is Result.Success -> _state.value = UsersViewState.Success(result.data)
                        is Result.Error -> _state.value = UsersViewState.Error(result.exception?.message)
                    }
                }
            }
        }

        fun onEvent(usersViewEvent: UsersViewEvent) {
            when (usersViewEvent) {
                is UsersViewEvent.OnCardClick ->
                    navigationEventsChannel.trySend(
                        NavigationEvent.NavigateToUserDetails(usersViewEvent.user),
                    )
            }
        }
    }
