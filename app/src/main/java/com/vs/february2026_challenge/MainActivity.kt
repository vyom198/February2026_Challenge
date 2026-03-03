package com.vs.february2026_challenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.vs.february2026_challenge.missedMessages.MissedMessagesRoot
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vs.february2026_challenge.stillConnected.StillConnectedRoot
import com.vs.february2026_challenge.stillConnected.StillConnectedScreen
import com.vs.february2026_challenge.ui.theme.February2026_ChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            February2026_ChallengeTheme {
                //StillConnectedRoot()
                MissedMessagesRoot()
               
            }
        }
    }
}

