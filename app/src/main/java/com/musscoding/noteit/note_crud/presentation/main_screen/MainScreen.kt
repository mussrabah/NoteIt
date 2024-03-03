package com.musscoding.noteit.note_crud.presentation.main_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.musscoding.noteit.R
import com.musscoding.noteit.note_crud.presentation.main_screen.components.NoteCard
import com.musscoding.noteit.note_crud.presentation.main_screen.components.SearchBar
import com.musscoding.noteit.sign_in.presentation.signin.UserData

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    onNoteCreateClick: () -> Unit,
    userData: UserData?
) {
    val state = viewModel.state
    viewModel.setUserData(userData)
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNoteCreateClick,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.create_a_note)
                )
            }
        }
    ) { paddingValues ->
        val padding = paddingValues
        Column(
            modifier  = modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            SearchBar(profilePhoto = state.signedInUser?.profilePictureUrl)
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(1)
            ) {
/*
            item {
                CanvasNoteCard()
            }

 */
                //Spacer(modifier = Modifier.height(5.dp))
                items(state.notes.size) {
                    NoteCard(
                        note = state.notes[it],
                        onDeleteNote = {
                            viewModel.onEvent(MainScreenEvent.OnDeleteNote(state.notes[it]))
                        }
                    )
                }

            }
        }
    }

}