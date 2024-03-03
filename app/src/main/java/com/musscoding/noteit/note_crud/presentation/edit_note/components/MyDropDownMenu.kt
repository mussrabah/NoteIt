package com.musscoding.noteit.note_crud.presentation.edit_note.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.musscoding.noteit.R

@Composable
fun MyDropDownMenu(
    modifier: Modifier = Modifier,
    menuItems: List<String>,
    isVisible: Boolean = false,
    onDismiss: () -> Unit,
    showToast: (String) -> Unit,
    selectedAction: (String) -> Unit,
    preselectedItem: Int = -1,
    isItForAppBar: Boolean
) {
    AnimatedVisibility(
        visible = isVisible,
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
    ) {
        DropdownMenu(
            expanded = isVisible,
            offset = DpOffset(0.dp, (-35).dp),
            onDismissRequest = { onDismiss() }) {
            menuItems.forEachIndexed {index, value ->
                DropdownMenuItem(
                    trailingIcon = {
                      if (preselectedItem == index) {
                          Icon(
                              imageVector = Icons.Default.Check,
                              contentDescription = "This is the selected font size"
                          )
                      }
                    },
                    leadingIcon = {
                              if (isItForAppBar) {
                                  Icon(
                                      imageVector = when(value) {
                                          stringResource(R.string.delete) -> {
                                              Icons.Default.Delete
                                          }
                                          stringResource(R.string.send) -> {
                                              Icons.Default.Share
                                          }
                                          stringResource(R.string.labels) -> {
                                              Icons.Default.Star
                                          }
                                          else -> {
                                              Icons.Default.Delete
                                          }
                                      },
                                      contentDescription = stringResource(R.string.this_note, value)
                                  )
                              }
                    },
                    colors = if (preselectedItem == index)
                        MenuDefaults.itemColors(textColor = MaterialTheme.colorScheme.primary)
                    else MenuDefaults.itemColors(),
                    onClick = {
                        showToast("You clicked $value menu")
                        selectedAction(value)
                        onDismiss()
                    },
                    text = {
                        Text(text = value)
                    }
                )
            }
        }
    }
    }
