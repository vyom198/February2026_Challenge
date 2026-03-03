package com.vs.february2026_challenge

import com.vs.february2026_challenge.missedMessages.MissedMessagesViewModel
import com.vs.february2026_challenge.stillConnected.ConnectionService
import com.vs.february2026_challenge.stillConnected.ConnectivityProvider
import com.vs.february2026_challenge.stillConnected.StillConnectedViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::StillConnectedViewModel)
    viewModelOf(::MissedMessagesViewModel)
    singleOf(:: ConnectionService) bind ConnectivityProvider::class
}