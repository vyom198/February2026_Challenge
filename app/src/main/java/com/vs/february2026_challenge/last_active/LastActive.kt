package com.vs.february2026_challenge.last_active

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.compose.rememberLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vs.february2026_challenge.R
import com.vs.february2026_challenge.ui.theme.February2026_ChallengeTheme
import com.vs.february2026_challenge.ui.theme.UIColors
import com.vs.february2026_challenge.ui.theme.figregular
import com.vs.february2026_challenge.ui.theme.figtitle
import com.vs.february2026_challenge.ui.theme.lastactivetitle
import com.vs.february2026_challenge.ui.theme.title
import org.koin.androidx.compose.koinViewModel

@Composable
fun LastActiveRoot(
    viewModel: LastActiveViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LifecycleEventEffect(
        lifecycleOwner = rememberLifecycleOwner(),
        event = Lifecycle.Event.ON_STOP,

    ) {
        viewModel.onAction(LastActiveAction.onStore)
    }
    LastActiveScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LastActiveScreen(
    state: LastActiveState,
    onAction: (LastActiveAction) -> Unit,
) {
    Scaffold(
        containerColor = Color(0xffF4F9FF),
        topBar = {

            CenterAlignedTopAppBar(
                modifier = Modifier.padding(top = 24.dp),
                title = {
                    Text(
                        text = "Activity status",
                        style = MaterialTheme.typography.lastactivetitle,
                        color = Color(0xff071121)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(44.dp)
                            .clip(CircleShape).border(
                                width = 1.dp,
                                color = Color(0xffD7E0ED),
                                shape = CircleShape

                            )

                    ) {
                        Icon(
                            painter = painterResource(R.drawable.arrow_left),
                            contentDescription = "Back",
                            tint = Color(0xff47566E),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                colors = androidx.compose.material3.TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color.Transparent,
                )
            )
        }

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues).padding(horizontal = 16.dp)
                .background(
                    Color(0xffF4F9FF)
                ),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Spacer(modifier = Modifier.height(48.dp))
            Image(
                painter = painterResource(R.drawable.avatar_img),
                contentDescription = null,
                modifier = Modifier.size(140.dp).clip(
                    shape = CircleShape
                )

            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Alice Cooper",
                style = MaterialTheme.typography.figtitle,
                color = Color(0xff071121)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = state.status,
                style = MaterialTheme.typography.figregular,
                color = Color(0xff47566E)
            )
        }
    }
}

//@Preview
//@Composable
//private fun Preview() {
//    February2026_ChallengeTheme {
//        LastActiveScreen(
//            state = LastActiveState(),
//            onAction = {}
//        )
//    }
//}