package com.vs.february2026_challenge.stillConnected

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vs.february2026_challenge.R
import org.koin.androidx.compose.koinViewModel


@Composable
fun StillConnectedRoot(
    viewModel: StillConnectedViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    StillConnectedScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun StillConnectedScreen(
    state: StillConnectedState,
    onAction: (StillConnectedAction) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
            ) {
                when (state.connectionState) {
                    ConnectionState.CONNECTED -> {
                        StillConnected()

                    }
                    ConnectionState.CONNECTION_LOST -> ConnectionLost()
                    ConnectionState.AIRPLANE_MODE_ACTIVE -> Disabled()
                }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = state.connectionState.message , style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface)


        }

    }
}


@Composable
fun StillConnected(modifier: Modifier = Modifier) {
    Box(modifier = modifier.wrapContentSize(),
         contentAlignment = Alignment.Center
        ){
        GradientCircleBox()

        Image(
            painter = painterResource(id = R.drawable.heart),
            contentDescription = null,
            modifier = Modifier.size(300.dp,233.dp)
        )
    }
}
@Composable
fun ConnectionLost(modifier: Modifier = Modifier) {
    Box(modifier = modifier.wrapContentSize(),
        contentAlignment = Alignment.Center
    ){
        GradientCircleBox()
        Image(
            painter = painterResource(id = R.drawable.heart_break),
            contentDescription = null,
            modifier = Modifier.size(220.dp)
        )
    }
}

@Composable
fun  Disabled(modifier: Modifier = Modifier) {
    Box(modifier = modifier.wrapContentSize(),
        contentAlignment = Alignment.Center
    ){
        GradientCircleBox()
        Image(
            painter = painterResource(id = R.drawable.aeroplane),
            contentDescription = null,
            modifier = Modifier.size(300.dp , 233.dp)
        )
    }
}
@Composable
fun GradientCircleBox() {
    val surfaceColor = Color(0xFFFFD7E6)   // 100% Opacity
    val surface20Color = Color(0x33FFD7E6) // 20% Opacity

    Box(
        modifier = Modifier
            .size(220.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(surfaceColor, surface20Color)
                ),
                shape = CircleShape
            ),
    )
}

//@Preview
//@Composable
//private fun Preview() {
//    February2026_ChallengeTheme {
//       Disabled()
//    }
//}