package com.felpster.userslist.presentation.home

import com.felpster.coreui.theme.AppTheme
import com.felpster.userslist.commons.FakeUserRepository
import com.felpster.userslist.commons.ScreenshotTest
import org.junit.Test

class UsersScreenSnapshotTest : ScreenshotTest() {
    @Test
    fun snapshot_success_state() {
        paparazzi.snapshot {
            AppTheme {
                UsersScreen(
                    viewState = UsersViewState.Success(FakeUserRepository.usersList),
                    onEvent = {}
                )
            }
        }
    }

    @Test
    fun snapshot_error_state() {
        paparazzi.snapshot {
            AppTheme {
                UsersScreen(
                    viewState = UsersViewState.Error(null),
                    onEvent = {}
                )
            }
        }
    }

    @Test
    fun snapshot_loading_state() {
        paparazzi.snapshot {
            AppTheme {
                UsersScreen(
                    viewState = UsersViewState.Loading(null),
                    onEvent = {}
                )
            }
        }
    }
}
