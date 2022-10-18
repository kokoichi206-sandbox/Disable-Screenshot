package jp.mydns.kokoichi0206.disable.screenshot.android

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainView(
    state: String,
    onButtonClicked: () -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.Red
                ),
                onClick = {
                    onButtonClicked()
                }
            ) {
                Text(
                    text = "Toggle FLAG",
                    fontSize = 30.sp,
                )
            }
            Text(
                modifier = Modifier.padding(4.dp),
                text = "State: $state",
                color = Color.Black,
            )
        }

        // 動画用に動きのあるもの。
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Box(
                modifier = Modifier
                    .greenBoxRotate(
                        size = 100.dp,
                        duration = 3000,
                    )
            )
            Box(
                modifier = Modifier
                    .greenBoxRotate(
                        size = 200.dp,
                        duration = 1200,
                    )
            )
        }
    }
}

// Composition context: にアクセスできる
fun Modifier.greenBoxRotate(
    size: Dp,
    duration: Int,
): Modifier = composed {

    val transition = rememberInfiniteTransition()
    val angleRatio by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = duration
            },
            repeatMode = RepeatMode.Reverse,
        ),
    )

    size(size)
        .graphicsLayer(
            rotationZ = 360f * angleRatio,
            alpha = 1f * angleRatio,
        )
        .background(Color.Green)
}
