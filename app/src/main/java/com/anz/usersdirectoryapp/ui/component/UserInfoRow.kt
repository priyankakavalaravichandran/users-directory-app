package com.anz.usersdirectoryapp.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.anz.usersdirectoryapp.ui.theme.UsersDirectoryAppTheme

/**
 * A reusable row component to display an icon followed by a text label.
 *
 * @param icon The [ImageVector] to be displayed as an icon.
 * @param text The text string to be displayed next to the icon.
 * @param topPadding The top padding in dp. Defaults to 4.
 * @param tint The color to be applied to both the icon and the text. Defaults to [MaterialTheme.colorScheme.onSurfaceVariant].
 */
@Composable
fun UserInfoRow(
    icon: ImageVector,
    text: String,
    topPadding: Int = 4,
    tint: Color = MaterialTheme.colorScheme.onSurfaceVariant
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = topPadding.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(14.dp),
            tint = tint
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = tint,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserInfoRowPreview() {
    UsersDirectoryAppTheme {
        UserInfoRow(
            icon = Icons.Default.Person,
            text = "Nettie Roberts",
            tint = MaterialTheme.colorScheme.primary,
            topPadding = 2
        )
    }
}