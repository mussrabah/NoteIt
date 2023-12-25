package util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.ui.graphics.vector.ImageVector

data class FabAction(
    val icon: ImageVector,
    val contentDescription: String
)

val fabActions = listOf(
    FabAction(
        icon = Icons.Default.Add,
        contentDescription = "Create a note"
    ),
    FabAction(
        icon = Icons.Default.Check,
        contentDescription = "Add note"
    )
)
