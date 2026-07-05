package com.anz.usersdirectoryapp.ui.feature.userdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.anz.usersdirectoryapp.R
import com.anz.usersdirectoryapp.domain.uimodel.UserUiModel
import com.anz.usersdirectoryapp.ui.component.ErrorContent
import com.anz.usersdirectoryapp.ui.component.ProfileField
import com.anz.usersdirectoryapp.ui.component.UserAvatar
import com.anz.usersdirectoryapp.ui.theme.UsersDirectoryAppTheme
import com.anz.usersdirectoryapp.ui.feature.userdetail.state.UserDetailUiState
import com.anz.usersdirectoryapp.ui.feature.userdetail.viewmodel.UserDetailViewModel

/**
 * Composable that represents the User Detail screen.
 * Fetches and displays the profile details of a single user.
 *
 * @param userId The unique identifier of the user to be displayed.
 * @param onBackClick Callback invoked when the back button is clicked.
 * @param userDetailViewModel The ViewModel providing the user's detailed information.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    userId: Int,
    onBackClick: () -> Unit,
    userDetailViewModel: UserDetailViewModel = hiltViewModel()
) {
    val userDetailState by userDetailViewModel.uiState.collectAsState()

    LaunchedEffect(userId) {
        userDetailViewModel.loadUserDetailById(userId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.user_profile)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.navigate_back_to_user_directory)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (val state = userDetailState) {
                is UserDetailUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is UserDetailUiState.Success -> {
                    UserDetailContent(user = state.userDetail)
                }

                is UserDetailUiState.Error -> {
                    ErrorContent(
                        message = state.message,
                        onRetry = { userDetailViewModel.loadUserDetailById(userId) }
                    )
                }
            }
        }
    }
}

/**
 * Composable that renders the user profile content including avatar, name, and contact fields.
 *
 * @param user The UI model containing the user's detailed information.
 */
@Composable
private fun UserDetailContent(user: UserUiModel) {
    with(user) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    UserAvatar(imageUrl = photo, userName = name, size = 150.dp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = user.name,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = user.userName,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.Gray
                        )
                    )
                    Spacer(modifier = Modifier.height(28.dp))
                    ProfileField(
                        label = stringResource(R.string.your_email),
                        value = email,
                        leadingIcon = Icons.Default.Email
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ProfileField(
                        label = stringResource(R.string.phone_number),
                        value    = phone,
                        leadingIcon = Icons.Default.Phone
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ProfileField(
                        label = stringResource(R.string.address),
                        value    = address,
                        leadingIcon = Icons.Default.LocationOn
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ProfileField(
                        label = stringResource(R.string.company_name),
                        value    = company,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserDetailScreenPreview() {
    UsersDirectoryAppTheme {
        UserDetailScreen(
            userId = 1,
            onBackClick = {
                //TODO Handle Back Click
            }
        )
    }
}