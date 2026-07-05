package com.anz.usersdirectoryapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anz.usersdirectoryapp.domain.uimodel.UserUiModel
import com.anz.usersdirectoryapp.ui.theme.UsersDirectoryAppTheme

/**
 * A composable representing a single item in the user list.
 * Displays user information such as name, username, phone, and address in a card layout.
 *
 * @param userUiModel The UI model containing the user's data to be displayed.
 * @param onClick A callback function invoked when the item is clicked, passing the user's ID.
 */
@Composable
fun UserListItem(
    userUiModel: UserUiModel,
    onClick: (Int) -> Unit
) {
    with(userUiModel) {
        Card(
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .clickable {
                    onClick(userUiModel.id)
                },
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Row(
                modifier = Modifier.padding(10.dp).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                UserAvatar(imageUrl = photo, userName = name)
                Spacer(modifier = Modifier.width(5.dp))
                Column {
                    Text(text = name, style = MaterialTheme.typography.titleMedium)
                    UserInfoRow(
                        icon = Icons.Default.Person,
                        text = userName,
                        tint = MaterialTheme.colorScheme.primary,
                        topPadding = 2
                    )
                    UserInfoRow(
                        icon = Icons.Default.Phone,
                        text = phone,
                        topPadding = 4
                    )
                    UserInfoRow(
                        icon = Icons.Default.LocationOn,
                        text = address,
                        topPadding = 4
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserListItemPreview() {
    UsersDirectoryAppTheme {
        UserListItem(
            userUiModel = UserUiModel(
                id = 1,
                name = "Nettie Roberts",
                company = "Crona, Harris and Rodriguez",
                userName = "Ewald.White96",
                email = "Ida_Funk49@yahoo.com",
                address = "802 Israel Spurs",
                phone = "1-469-957-6150",
                photo = "https://json-server.dev/ai-profiles/17.png"
            )
        ) {

        }
    }
}
