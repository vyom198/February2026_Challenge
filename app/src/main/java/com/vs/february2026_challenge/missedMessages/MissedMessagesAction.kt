package com.vs.february2026_challenge.missedMessages

sealed interface MissedMessagesAction {
 data object  onButtonClick : MissedMessagesAction
    data object  onResume : MissedMessagesAction
}