package com.vs.february2026_challenge.stillConnected

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StillConnectedViewModel(
    private val connectionProvider: ConnectivityProvider
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(StillConnectedState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                viewModelScope.launch {
                    connectionProvider.connectivityState.collect { connectionState ->
                        Log.d("StillConnectedViewModel", "State: $connectionState")
                        _state.update {
                            it.copy(connectionState = connectionState)
                        }
                    }
                }


            }
            }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = StillConnectedState()
        )

    fun onAction(action: StillConnectedAction) {
        when (action) {
            else -> TODO("Handle actions")
        }
    }

}