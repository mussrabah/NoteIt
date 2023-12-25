package com.musscoding.noteit.presentation.main_screen.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.musscoding.noteit.domain.model.Note

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    note: Note,
    titleSize: TextUnit = 16.sp,
    textColor: Color = MaterialTheme.colorScheme.primary,
    background: Color = MaterialTheme.colorScheme.primaryContainer,
    onDeleteNote: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth()
            .height(200.dp)
            .alpha(.7f)
            .drawBehind {
                val path = Path().apply {
                    moveTo(0f, 0f)
                    lineTo(size.width, 0f)
                    lineTo(size.width, size.height / 2 - 10.dp.toPx())
                    /*
                    arcTo(
                        rect = Rect(
                            left = size.height / 2 - 10.dp.toPx(),
                            top = 0f,
                            right = size.height / 2,
                            bottom = 10.dp.toPx()
                        ),
                        startAngleDegrees = -90.0f,
                        sweepAngleDegrees = 90.0f,
                        forceMoveTo = false
                    )

                     */
                    drawArc(
                        color = background,
                        startAngle = 0f,
                        sweepAngle = 90f,
                        size = Size(
                            width = size.width - (2 * size.width / 3) - 20.dp.toPx(),
                            height = 80.dp.toPx()
                        ),
                        useCenter = false,
                        style = Stroke(2.dp.toPx()),
                        topLeft = Offset(
                            x = 2 * size.width / 3 + 20.dp.toPx(),
                            y = ((size.height / 2) - 10.dp.toPx()) - 80.dp.toPx() / 2
                        )
                    )
                    drawArc(
                        color = background,
                        startAngle = -90f,
                        sweepAngle = -90f,
                        size = Size(
                            width = size.width - (2 * size.width / 3) - 10.dp.toPx(),
                            height = 50.dp.toPx()
                        ),
                        useCenter = false,
                        style = Stroke(2.dp.toPx()),
                        topLeft = Offset(
                            x = 2 * size.width / 3 + 10.dp.toPx() + 10.dp.toPx(),
                            y = size.height / 2 + 30.dp.toPx()
                        )
                    )
                    drawArc(
                        color = background,
                        startAngle = 90f,
                        sweepAngle = -90f,
                        size = Size(
                            width = size.width - (2 * size.width / 3) - 10.dp.toPx(),
                            height = size.height / 3 + 23.dp.toPx()
                        ),
                        useCenter = false,
                        style = Stroke(2.dp.toPx()),
                        topLeft = Offset(
                            x = size.width / 3 + 10.dp.toPx() + 10.dp.toPx() + 10.dp.toPx(),
                            y = size.height - (size.height / 3 + 23.dp.toPx())
                        )
                    )
                    moveTo(size.width - (size.width / 3) - 28.dp.toPx(), size.height)
                    lineTo(0f, size.height)
                    lineTo(0f, 0f)
                    //lineTo(size.width - (size.width / 3), size.height / 2)
                    //lineTo(size.width - (size.width / 3), size.height)
                    //lineTo(0f, size.height)
                    //lineTo(0f, size.height)
                    //lineTo(0f, 0f)
                }
                drawPath(
                    path = path,
                    color = background,
                    //style = Stroke(width = 2.dp.toPx())
                )
                /*
                drawCircle(
                    color = textColor,
                    radius = (size.height - (size.height / 2 + 30.dp.toPx())) / 2 - 5.dp.toPx(),
                    center = Offset(
                        x = size.width - (size.width - (2 * size.width / 3 + 10.dp.toPx() + 10.dp.toPx())) / 2 + 5.dp.toPx(),
                        y = size.height - (size.height - (size.height / 2 + 30.dp.toPx())) / 2 + 5.dp.toPx()
                    )
                )
                */
                /*
                val path = Path().apply {
                    moveTo(50f, 0f)
                    lineTo(size.width - 50f, 0f)
                    addArc(
                        Rect(
                            offset = Offset(size.width - 100f, 0f),
                            size = Size(100f, 100f)
                        ),
                        startAngleDegrees = 270f,
                        sweepAngleDegrees = 90f
                    )
                    lineTo(size.width, size.height - 50f)
                    addArc(
                        Rect(
                            offset = Offset(size.width - 100f, size.height - 100f),
                            size = Size(100f, 100f)
                        ),
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = 90f
                    )
                    lineTo(50f, size.height)
                    addArc(
                        Rect(
                            offset = Offset(0f, size.height - 100f),
                            size = Size(100f, 100f)
                        ),
                        startAngleDegrees = 90f,
                        sweepAngleDegrees = 90f
                    )
                    lineTo(0f, 50f)
                    addArc(
                        Rect(
                            offset = Offset(0f, 0f),
                            size = Size(100f, 100f)
                        ),
                        startAngleDegrees = 180f,
                        sweepAngleDegrees = 90f
                    )
                    moveTo(0f,0f)
                }

                drawPath(
                    path = path,
                    color = background,
                    style = Stroke(
                        width = 2.dp.toPx()
                    )
                )

                 */

            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 10.dp, top = 5.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Column {
                FloatingActionButton(
                    onClick = { /*TODO*/ },
                    elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 10.dp),
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    shape = CircleShape
                )
                {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Add to favorite"
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                FloatingActionButton(
                    onClick = {
                              onDeleteNote()
                    },
                    elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 10.dp),
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    shape = CircleShape
                )
                {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete this note"
                    )
                }
            }

        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 5.dp, bottom = 5.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit This Note",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    //.padding(horizontal = 16.dp, vertical = 20.dp)
                    .padding(start = 16.dp, top = 20.dp)
            ) {
                Text(
                    text = note.label,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = note.title,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(start = 16.dp, bottom = 16.dp)
            ) {
                Text(
                    text = note.content,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Thin,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.secondary,
                    maxLines = 2
                )
            }

            Row(
                modifier = Modifier
                    //.fillMaxWidth(0.7f)
                    .padding(start = 16.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.onPrimaryContainer)
                    .padding(start = 10.dp, end = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Edit this note",
                    tint = MaterialTheme.colorScheme.background
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = note.timeStamp.month.toString().take(3) +
                            " ${note.timeStamp.dayOfMonth}, ${note.timeStamp.year}" +
                            " ${note.timeStamp.toLocalTime().toString()
                                .substringBefore('.')}",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.background
                )
            }

        }
    }
}