package com.felpster.userslist.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.felpster.userslist.R
import com.felpster.userslist.domain.model.User
import com.felpster.userslist.ui.components.UserCard
import com.felpster.userslist.ui.theme.AppTheme

@Composable
fun UsersScreen(viewState: UsersViewState) {
    when (viewState) {
        is UsersViewState.Error -> {}
        is UsersViewState.Loading -> {}
        is UsersViewState.Success -> UsersContent(viewState.users)
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun UsersContent(users: List<User>) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                title = { Text(stringResource(R.string.app_name)) },
            )
        },
    ) {
        LazyColumn(
            modifier =
                Modifier
                    .fillMaxHeight()
                    .padding(it),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(users) { user ->
                UserCard(name = user.name, email = user.email) {
                    Toast.makeText(context, "Card clicked", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UsersScreenPreview() {
    AppTheme {
        UsersScreen(
            UsersViewState.Success(
                listOf(
                    User(
                        id = 0,
                        name = "Felipe",
                        username = "username",
                        email = "felipe.valadares2@gmail.com",
                        website = "http://www.google.com",
                    ),
                    User(
                        id = 0,
                        name = "John",
                        username = "username",
                        email = "john@gmail.com",
                        website = "http://www.google.com",
                    ),
                ),
            ),
        )
    }
}
