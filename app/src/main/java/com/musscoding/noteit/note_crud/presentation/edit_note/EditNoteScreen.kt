package com.musscoding.noteit.note_crud.presentation.edit_note

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.musscoding.noteit.R
import com.musscoding.noteit.note_crud.presentation.edit_note.components.MyDropDownMenu
import util.UiEvent

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNote(
    modifier: Modifier = Modifier,
    viewModel: EditNoteViewModel = hiltViewModel(),
    onBackClicked: () -> Unit
) {
    val state = viewModel.state
    var textFieldAppBarValue by remember {
        mutableStateOf("")
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val expandedActionsMenuItem = remember {
        mutableStateOf(false)
    }
    val expandedFontSizeMenuItem = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    val menuItems = listOf(
        stringResource(id = R.string.delete),
        stringResource(id = R.string.send),
        stringResource(id = R.string.labels)
    )
    val fontSizes = (10..80)
        .filter {
            it % 10 == 0
        }
        .map {
        it.toString()
    }
    var selectedFont by remember {
        mutableIntStateOf(20)
    }
    var textField by remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {event ->
            when(event) {
                is UiEvent.Success -> {
                    onBackClicked()
                }
                else -> Unit
            }
        }
    }
    Scaffold(
        modifier = modifier.imePadding(),
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .padding(vertical = 10.dp),
                title = {
                    Row(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
                    ) {
                        BasicTextField(
                            value = textFieldAppBarValue,
                            onValueChange = {
                                textFieldAppBarValue = it
                            },
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(MaterialTheme.colorScheme.secondaryContainer)
                                .fillMaxWidth()
                                .height(40.dp),
                            singleLine = true,
                            textStyle = LocalTextStyle.current.copy(
                                fontSize = 24.sp,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.secondary
                            ),
                            interactionSource = interactionSource,
                            decorationBox = @Composable {innerTextField ->
                                if (textFieldAppBarValue.isEmpty()) {
                                    Text(
                                        text = stringResource(R.string.enter_a_title),
                                        color = MaterialTheme.colorScheme.secondary,
                                        fontSize = 24.sp,
                                        style = LocalTextStyle.current.copy(
                                            textAlign = TextAlign.Center
                                        )

                                    )
                                }
                                innerTextField()
                            }
                        )
                    }

                },
                actions = {
                    IconButton(onClick = {
                        expandedActionsMenuItem.value = true
                        }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = stringResource(id = R.string.menu)
                        )
                    }
                    MyDropDownMenu(
                        menuItems = menuItems,
                        onDismiss = { expandedActionsMenuItem.value = false },
                        showToast = {
                            Toast.makeText(
                                context,
                                it,
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        isVisible = expandedActionsMenuItem.value,
                        selectedAction = {

                        },
                        isItForAppBar = true
                    )

                },
                navigationIcon = {
                    IconButton(onClick = {
                        onBackClicked()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.menu)
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp)),
                containerColor = Color.Transparent,
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_check_box_24),
                            contentDescription = stringResource(R.string.add_check_button)
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_text_fields_24),
                            contentDescription = stringResource(R.string.open_text_options)
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_format_color_text_24),
                            contentDescription = stringResource(R.string.change_font_color)
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_font_download_24),
                            contentDescription = stringResource(R.string.change_font_background)
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Row(
                            modifier = Modifier
                                .clickable {
                                    expandedFontSizeMenuItem.value = true
                                }
                        ) {
                            Text(text = "$selectedFont")
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = stringResource(R.string.choose_font_size)
                            )
                        }
                    }
                    MyDropDownMenu(
                        menuItems = fontSizes,
                        onDismiss = {
                                    expandedFontSizeMenuItem.value = false
                        },
                        showToast = {
                            Toast
                                .makeText(
                                    context,
                                    it,
                                    Toast.LENGTH_SHORT
                                ).show()
                        },
                        isVisible = expandedFontSizeMenuItem.value,
                        selectedAction = {
                                         selectedFont = it.toInt()
                        },
                        preselectedItem = fontSizes.indexOfFirst {
                            it.toInt() == selectedFont
                        },
                        isItForAppBar = false
                    )
                },
                floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                if (textField.isEmpty() || textFieldAppBarValue.isEmpty()) {
                                    Toast
                                        .makeText(
                                            context,
                                            context.getString(R.string.please_fill_in_some_content),
                                            Toast.LENGTH_SHORT
                                        ).show()
                                } else {
                                    viewModel
                                        .onEvent(
                                            EditNoteEvent
                                                .OnSaveNote(textFieldAppBarValue, textField)
                                        )
                                }
                            },
                            containerColor = MaterialTheme.colorScheme.primary
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = stringResource(R.string.add_note)
                            )
                        }
                },
                tonalElevation = 36.dp
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 10.dp)
                .padding(bottom = 10.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
        ) {
            BasicTextField(
                value = textField,
                onValueChange = {
                                textField = it
                },
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize(),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = selectedFont.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            )
        }
    }
}