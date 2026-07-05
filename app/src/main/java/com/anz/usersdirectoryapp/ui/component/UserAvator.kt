package com.anz.usersdirectoryapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.anz.usersdirectoryapp.ui.theme.UsersDirectoryAppTheme

/**
 * A composable that displays a circular user avatar image.
 *
 * @param imageUrl The URL of the image to display.
 * @param userName The name of the user, used for the content description.
 * @param size The size of the avatar. Defaults to 56.dp.
 */
@Composable
fun UserAvatar(
    imageUrl: String,
    userName: String,
    size: Dp = 56.dp
) {
    val model = ImageRequest.Builder(LocalContext.current)
        .data(imageUrl)
        .crossfade(true)
        .build()

    AsyncImage(
        model = model,
        placeholder = rememberVectorPainter(Icons.Default.AccountCircle),
        contentDescription = userName,
        contentScale = ContentScale.Crop,
        error =rememberVectorPainter(Icons.Default.AccountCircle),
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surfaceVariant)
    )
}

@Preview(showBackground = true)
@Composable
fun UserAvatarPreview() {
    UsersDirectoryAppTheme {
        UserAvatar(
            imageUrl = "https://json-server.dev/ai-profiles/17.png",
            userName = "Nettie Roberts"
        )
    }
}
