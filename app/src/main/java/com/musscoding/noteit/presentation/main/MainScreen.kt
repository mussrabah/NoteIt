package com.musscoding.noteit.presentation.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.musscoding.noteit.domain.model.Note
import com.musscoding.noteit.presentation.main.components.NoteCard
import com.musscoding.noteit.presentation.main.components.SearchBar
import java.time.LocalDateTime

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),

) {
    val state = viewModel.state
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
            items(8) {
                NoteCard(
                    note = Note(
                        title = "My Note",
                        content = "Heeeeeellll Noooooo, is there any thing i can help with these days" +
                                "as im literally happy and having very stress less days" +
                                "it's just i'm beatiful mind inside an awful body" +
                                "surrounded by idiots everywhere",
                        label = "Im a label",
                        timeStamp = LocalDateTime.now()
                    )
                )
            }

        }
    }
}