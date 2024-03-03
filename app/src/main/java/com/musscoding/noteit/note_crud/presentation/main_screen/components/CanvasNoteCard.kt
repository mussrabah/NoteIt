package com.musscoding.noteit.note_crud.presentation.main_screen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun CanvasNoteCard(
    modifier: Modifier = Modifier,
    background: Color = MaterialTheme.colorScheme.primaryContainer,
    onBackground: Color = MaterialTheme.colorScheme.primary,
    imageBitmap: ImageVector = Icons.Default.Create
) {

    Canvas(
        modifier = modifier
            .padding(20.dp)
            //.background(background)
            .fillMaxWidth()
            .height(200.dp)
            .alpha(0.7f)
    ) {
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
                size = Size(width = size.width - (2*size.width / 3) - 20.dp.toPx(), height = 80.dp.toPx()),
                useCenter = false,
                style = Stroke(2.dp.toPx()),
                topLeft = Offset(x = 2*size.width / 3 + 20.dp.toPx(),
                    y = ((size.height / 2) - 10.dp.toPx()) - 80.dp.toPx()/2)
            )
            drawArc(
                color = background,
                startAngle = -90f,
                sweepAngle = -90f,
                size = Size(width = size.width - (2*size.width / 3) - 10.dp.toPx(), height = 50.dp.toPx()),
                useCenter = false,
                style = Stroke(2.dp.toPx()),
                topLeft = Offset(x = 2*size.width / 3 + 10.dp.toPx() + 10.dp.toPx(),
                    y = size.height / 2 + 30.dp.toPx())
            )
            drawArc(
                color = background,
                startAngle = 90f,
                sweepAngle = -90f,
                size = Size(width = size.width - (2*size.width / 3) - 10.dp.toPx(),
                    height = size.height / 3 + 23.dp.toPx()),
                useCenter = false,
                style = Stroke(2.dp.toPx()),
                topLeft = Offset(x = size.width / 3 + 10.dp.toPx() + 10.dp.toPx() + 10.dp.toPx(),
                    y = size.height - (size.height / 3 + 23.dp.toPx()))
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
        drawCircle(
            color = onBackground,
            radius = (size.height - (size.height / 2 + 30.dp.toPx())) / 2 - 5.dp.toPx(),
            center = Offset(
                x = size.width - (size.width - (2*size.width / 3 + 10.dp.toPx() + 10.dp.toPx()))/2 + 5.dp.toPx(),
                y = size.height - (size.height - (size.height / 2 + 30.dp.toPx())) / 2 + 5.dp.toPx()
            )
        )
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


}