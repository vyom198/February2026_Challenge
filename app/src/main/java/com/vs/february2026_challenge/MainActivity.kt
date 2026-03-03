package com.vs.february2026_challenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.vs.february2026_challenge.missedMessages.MissedMessagesRoot
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

