package com.anz.usersdirectoryapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anz.usersdirectoryapp.ui.theme.UsersDirectoryAppTheme

/**
 * A composable that displays a labeled profile field with an optional leading icon.
 *
 * @param label The text to be displayed as the label for the field.
 * @param value The text value to be displayed in the field.
 * @param leadingIcon An optional [ImageVector] to be displayed at the end of the field row.
 */
@Composable
fun ProfileField(
    label: String,
    value: String,
    leadingIcon: ImageVector? = null
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                color = Color.Gray,
                fontSize = 12.sp
            ),
            modifier = Modifier.padding(start = 4.dp, bottom = 6.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF5F5F5))
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF333333)
                ),
                modifier = Modifier.weight(1f)
            )
            leadingIcon?.let {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = Color(0xFFBDBDBD),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileFieldPreview() {
    UsersDirectoryAppTheme {
        ProfileField(
            label = "Email",
            value = "Priyanka.Peeka@gmail.com",
            leadingIcon = Icons.Default.Email
        )
    }
}