package com.felpster.userslist.presentation.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import com.felpster.coreui.components.ErrorLayoutTags
import com.felpster.coreui.components.LoadingLayoutTags
import com.felpster.coreui.theme.AppTheme
import com.felpster.userslist.commons.ComposeTest
import com.felpster.userslist.commons.FakeUserRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class UsersScreenTest : ComposeTest() {

    @Test
    fun `Ensure users content is displayed`() {
        with(composeTestRule) {
            setContent {
                AppTheme {
                    UsersScreen(
                        viewState = UsersViewState.Success(FakeUserRepository.fakeUsersList),
                        onEvent = {},
                    )
                }
            }

            onNodeWithTag(UsersContentLayoutTags.USERS_LAYOUT).assertIsDisplayed()
        }
    }

    @Test
    fun `Ensure error screen is displayed`() {
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
    fun `Ensure loading screen is displayed`() {
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
