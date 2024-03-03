package com.musscoding.noteit.note_crud.presentation.main_screen.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@OptIn(ExperimentalTextApi::class, ExperimentalFoundationApi::class)
@Composable
fun WaveAnimatedText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit,
    color: Color
) {

    val letters = mutableListOf<Animatable<Float, AnimationVector1D>>()
    var i = 0
    while (i < text.length) {
        letters.add(remember {
            Animatable(0f)
        })
        i++
    }
    letters.forEachIndexed { index, animatable ->
        LaunchedEffect(animatable) {
            delay(index * 100L)
            animatable.animateTo(
                targetValue = 1f,
                animationSpec = keyframes {
                        durationMillis = 2000
                        0.0f at 0 with LinearOutSlowInEasing
                        1.0f at 200 with LinearOutSlowInEasing
                        0.0f at 400 with LinearOutSlowInEasing
                        0.0f at 2000
                    },
                    //iterations = 2,
                    //repeatMode = RepeatMode.Restart
                    //repeatMode = RepeatMode.Restart,

            )
            animatable.animateTo(
                targetValue = 0f,
                animationSpec = keyframes {
                    durationMillis = 2000 // Set a very short duration for an immediate change
                }
            )
        }
    }

    val dys = letters.map { it.value }

    val travelDistance = with(LocalDensity.current) { 15.dp.toPx() }


    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
        ) {
        dys.forEachIndexed { index, dy ->
            Box(
                //Modifier.fillMaxSize()
                Modifier
                    .graphicsLayer {
                translationY = -dy * travelDistance
            }
            ) {
                Box(
                    Modifier
                        //.fillMaxSize()
                        .background(color = Color.Transparent)
                ) {
                    Text(
                        text = text[index].toString(),
                        fontSize = fontSize,
                        color = color
                    )
                }
            }
/*
            if (index != letters.size - 1) {
                Spacer(modifier = Modifier.width(10.dp))
            }

 */
        }
    }
/*
    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 8f,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = "scale"
    )
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = text,
            modifier = Modifier
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    transformOrigin = TransformOrigin.Center
                }
                .align(Alignment.Center),
            // Text composable does not take TextMotion as a parameter.
            // Provide it via style argument but make sure that we are copying from current theme
            style = LocalTextStyle.current.copy(textMotion = TextMotion.Animated)
        )
    }
*/
}