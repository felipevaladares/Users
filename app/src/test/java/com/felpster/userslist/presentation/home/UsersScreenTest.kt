package com.felpster.userslist.presentation.home

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.felpster.coreui.components.ErrorLayoutTags
import com.felpster.coreui.components.LoadingLayoutTags
import com.felpster.coreui.theme.AppTheme
import com.felpster.userslist.commons.ComposeTest
import com.felpster.userslist.commons.FakeUserRepository
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UsersScreenTest : ComposeTest() {

    @Test
    fun `Ensure users layout is displayed and items exists`() {
        val userList = FakeUserRepository.usersList
        with(composeTestRule) {
            setContent {
                AppTheme {
                    UsersScreen(
                        viewState = UsersViewState.Success(userList),
                        onEvent = {},
                    )
                }
            }

            onNodeWithTag(UsersContentLayoutTags.USERS_LAYOUT).assertIsDisplayed()
            onNodeWithTag(UsersContentLayoutTags.USERS_LAYOUT).onChildren().assertCountEquals(userList.size)
        }
    }

    @Test
    fun `Ensure error layout is displayed`() {
        with(composeTestRule) {
            setContent {
                AppTheme {
                    UsersScreen(viewState = UsersViewState.Error(null), onEvent = {})
                }
            }

            onNodeWithTag(ErrorLayoutTags.ERROR_LAYOUT).assertIsDisplayed()
        }
    }

    @Test
    fun `Ensure loading layout is displayed`() {
        with(composeTestRule) {
            setContent {
                AppTheme {
                    UsersScreen(viewState = UsersViewState.Loading(null), onEvent = {})
                }
            }

            onNodeWithTag(LoadingLayoutTags.LOADING_LAYOUT).assertIsDisplayed()
        }
    }
}
