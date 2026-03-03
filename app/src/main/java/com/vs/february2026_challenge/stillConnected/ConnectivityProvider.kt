package com.vs.february2026_challenge.stillConnected

import kotlinx.coroutines.flow.Flow

enum class ConnectionState(val message: String) {
    CONNECTED("You’re Connected"),
    CONNECTION_LOST("We Lost the Connection"),
    AIRPLANE_MODE_ACTIVE("You Turned on Airplane Mode")
}

interface ConnectivityProvider {
    val connectivityState: Flow<ConnectionState>
}