package com.vs.february2026_challenge.last_active

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LastActiveViewModel(
private val userPreferencesRepository: UserPreferencesRepository,
    private val context : Context
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(LastActiveState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                viewModelScope.launch {
                    userPreferencesRepository.lastActiveTimestamp.collect { timestamp ->
                        if (timestamp != null) {
                           val status = formatActivityStatus(timestamp)
                            _state.update {
                                it.copy(status = status)
                            }
                        }
                    }
                }
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = LastActiveState()
        )

    fun onAction(action: LastActiveAction) {
        when (action) {
            LastActiveAction.onStore -> viewModelScope.launch {
                userPreferencesRepository.saveLastActive(System.currentTimeMillis())

            }
        }
    }

}