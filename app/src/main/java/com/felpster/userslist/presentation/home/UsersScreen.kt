package com.felpster.userslist.presentation.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.felpster.userslist.R
import com.felpster.userslist.domain.model.User
import com.felpster.userslist.ui.components.UserCard
import com.felpster.userslist.ui.theme.AppTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun UsersScreen(viewState: UsersViewState) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                    )
                },
            )
        },
    ) { padding ->
        when (viewState) {
            is UsersViewState.Error -> ErrorLayout(padding, viewState.message)
            is UsersViewState.Success -> UsersLayout(padding, viewState.users)
            is UsersViewState.Loading -> LoadingLayout(padding, viewState.message)
        }
    }
}

@Composable
private fun ErrorLayout(
    padding: PaddingValues,
    message: String?,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.sad_face),
            contentDescription = null,
        )

        Text(
            modifier = Modifier.padding(top = 8.dp),
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Black),
            text = message ?: stringResource(id = R.string.generic_error_message),
        )
    }
}

@Composable
private fun LoadingLayout(
    padding: PaddingValues,
    message: String?,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )

        message?.let {
            Text(
                modifier = Modifier.padding(top = 32.dp),
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium),
                text = it,
            )
        }
    }
}

@Composable
private fun UsersLayout(
    padding: PaddingValues,
    users: List<User>,
) {
    val context = LocalContext.current
    LazyColumn(
        modifier =
            Modifier
                .fillMaxHeight()
                .padding(padding),
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

@Preview(showBackground = true)
@Composable
fun UsersScreenLoadingPreview() {
    AppTheme {
        UsersScreen(
            UsersViewState.Loading("Retrieving users list..."),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UsersScreenErrorPreview() {
    AppTheme {
        UsersScreen(
            UsersViewState.Error(null),
        )
    }
}
