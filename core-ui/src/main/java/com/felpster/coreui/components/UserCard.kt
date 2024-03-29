package com.felpster.coreui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.felpster.coreui.components.UserCardLayoutTags.CARD_LAYOUT
import com.felpster.coreui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserCard(
    name: String,
    email: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    ElevatedCard(
        modifier =
            modifier
                .testTag(CARD_LAYOUT)
                .fillMaxWidth()
                .wrapContentHeight(),
        onClick = onClick,
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "Name: $name",
                style = MaterialTheme.typography.titleLarge,
            )

            Text(
                text = "Email: $email",
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserCardPreview() {
    AppTheme {
        Column(modifier = Modifier.padding(8.dp)) {
            UserCard("Felipe", "felipe.valadares2@gmail.com") {
            }
        }
    }
}

object UserCardLayoutTags {
    const val CARD_LAYOUT = "UserCardLayoutTags_content"
}
