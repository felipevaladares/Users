package com.felpster.userslist.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felpster.userslist.commons.Result
import com.felpster.userslist.commons.asResult
import com.felpster.userslist.domain.model.User
import com.felpster.userslist.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class UsersViewState {
    data class Success(val users: List<User>) : UsersViewState()

    data class Error(val exception: Throwable?) : UsersViewState()

    data object Loading : UsersViewState()
}

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    private val _state = MutableStateFlow<UsersViewState>(UsersViewState.Loading)
    val state: StateFlow<UsersViewState> = _state

    init {
        viewModelScope.launch {
            userRepository.getUsers().asResult().collect { result ->
                when (result) {
                    is Result.Loading -> _state.value = UsersViewState.Loading
                    is Result.Success -> _state.value = UsersViewState.Success(result.data)
                    is Result.Error -> _state.value = UsersViewState.Error(result.exception)
                }
            }
        }
    }
}
