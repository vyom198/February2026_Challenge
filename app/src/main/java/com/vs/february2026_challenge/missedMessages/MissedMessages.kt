package com.vs.february2026_challenge.missedMessages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vs.february2026_challenge.R
import com.vs.february2026_challenge.ui.theme.February2026_ChallengeTheme
import com.vs.february2026_challenge.ui.theme.UIColors
import com.vs.february2026_challenge.ui.theme.buttonTitle
import com.vs.february2026_challenge.ui.theme.regular
import com.vs.february2026_challenge.ui.theme.subtitle
import com.vs.february2026_challenge.ui.theme.title
import org.koin.androidx.compose.koinViewModel
import java.util.function.IntConsumer

@Composable
fun MissedMessagesRoot(
    viewModel: MissedMessagesViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        viewModel.onAction(MissedMessagesAction.onResume)
    }
    MissedMessagesScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MissedMessagesScreen(
    state: MissedMessagesState,
    onAction: (MissedMessagesAction) -> Unit,
) {
    Scaffold(
        containerColor = UIColors.background,
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = "Notifications",
                        style = MaterialTheme.typography.title,
                        color = UIColors.textPrimary
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(32.dp)
                            .clip(CircleShape).background(color = Color.White)

                    ) {
                        Icon(
                            painter = painterResource(R.drawable.arrow_left),
                            contentDescription = "Back",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(16.dp)
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
                    UIColors.background
                ),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
              NotificationCard(
                  isEnabled = state.isEnabled,
                  onClick = { onAction(MissedMessagesAction.onButtonClick) }
              )

        }

    }
}

@Composable
fun NotificationCard(
    isEnabled: Boolean,
    onClick : () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth().height(272.dp).clip(
            shape = RoundedCornerShape(12.dp)
        ).background(
            color = Color.White,

            ).padding(

                top = 32.dp,


        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.size(56.dp).clip(
                shape = CircleShape
            ).background(
                color = if(isEnabled) UIColors.successBg else UIColors.errorBg

            ),
            contentAlignment = Alignment.Center

        ) {
            Icon(
                painter = painterResource(id = if(isEnabled)R.drawable.bell_01 else R.drawable.bell_off_01),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)

            )


        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = if(isEnabled)"You will receive notifications" else "Notifications are turned off",
            style = MaterialTheme.typography.subtitle,
            textAlign = TextAlign.Center,
            color = UIColors.textPrimary
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = if(isEnabled)"Notifications are enabled for this app." else "You won’t see pop-up notifications when the app is in the background.",
            style = MaterialTheme.typography.regular,
            textAlign = TextAlign.Center,
            color = UIColors.textSecondary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(100),
            modifier = Modifier.width(332.dp),
            border = BorderStroke(
                width = 1.dp,
                color = if(isEnabled) UIColors.successBg else UIColors.button
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = if(isEnabled)Color.Transparent else UIColors.button,
                contentColor = if(isEnabled) UIColors.textSecondary else Color.White,

            )
        ) {
            Text(
                text = "Open System Settings",
                style = MaterialTheme.typography.buttonTitle,
            )
        }

    }
}

@Preview
@Composable
private fun Preview() {
    February2026_ChallengeTheme {
        MissedMessagesScreen(
            state = MissedMessagesState(),
            onAction = {}
        )
    }
}