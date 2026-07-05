package com.anz.usersdirectoryapp.ui.feature.userlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.anz.usersdirectoryapp.domain.uimodel.UserUiModel
import com.anz.usersdirectoryapp.ui.component.ErrorContent
import com.anz.usersdirectoryapp.ui.component.UserListItem
import com.anz.usersdirectoryapp.ui.theme.Typography
import com.anz.usersdirectoryapp.ui.theme.UsersDirectoryAppTheme
import com.anz.usersdirectoryapp.R
import com.anz.usersdirectoryapp.ui.feature.userlist.state.UserListUiState
import com.anz.usersdirectoryapp.ui.feature.userlist.viewmodel.UserListViewModel

/**
 * Composable that represents the User List screen.
 * It manages the display states (Loading, Success, Error) and navigates to details on user click.
 *
 * @param onUserClick Callback invoked when a user item is clicked, providing the user's ID.
 * @param userListViewModel The ViewModel that provides the UI state for this screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    onUserClick: (Int) -> Unit,
    userListViewModel: UserListViewModel = hiltViewModel<UserListViewModel>()
) {
    val uiState by userListViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.user_directory))
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val state = uiState) {
                is UserListUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is UserListUiState.Success -> {
                    UserListContent(
                        users = state.user,
                        onClick = onUserClick
                    )
                }

                is UserListUiState.Error -> {
                    ErrorContent(
                        message = state.message,
                        onRetry = { userListViewModel.retry() }
                    )
                }
            }
        }
    }
}

/**
 * Composable that displays the list of users or a message if the list is empty.
 *
 * @param users The list of [UserUiModel] to be displayed.
 * @param onClick Callback invoked when a user item is clicked.
 */
@Composable
fun UserListContent(users: List<UserUiModel>, onClick: (Int) -> Unit) {

    if (users.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.no_users_found),
                style = Typography.bodyLarge
            )
        }
    } else {
        LazyColumn(
            state = rememberLazyListState(),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(items = users) { user ->
                UserListItem(
                    userUiModel = user,
                    onClick = onClick
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserListScreenPreview() {
    UsersDirectoryAppTheme {
        UserListScreen(
            onUserClick = {
              //Todo Perform User List Click Action
            }
        )
    }
}