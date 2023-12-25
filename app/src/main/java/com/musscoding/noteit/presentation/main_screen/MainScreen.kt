package com.musscoding.noteit.presentation.main_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.musscoding.noteit.Route
import com.musscoding.noteit.domain.model.Note
import com.musscoding.noteit.presentation.main_screen.components.NoteCard
import com.musscoding.noteit.presentation.main_screen.components.SearchBar
import kotlinx.coroutines.flow.collect
import util.UiEvent
import util.fabActions
import java.time.LocalDateTime

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    onNoteCreateClick: () -> Unit
) {
    val state = viewModel.state
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNoteCreateClick,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Create a note"
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
            SearchBar()
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