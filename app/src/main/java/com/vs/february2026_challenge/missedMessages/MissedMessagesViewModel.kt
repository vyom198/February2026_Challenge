package com.vs.february2026_challenge.missedMessages

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class MissedMessagesViewModel(
    private val context : Context
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(MissedMessagesState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                checkSelfPermission()
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = MissedMessagesState()
        )

    fun onAction(action: MissedMessagesAction) {
        when (action) {
            MissedMessagesAction.onButtonClick -> {
                openAppSettings()
            }
            MissedMessagesAction.onResume -> {
                checkSelfPermission()
            }
        }
    }
   private fun checkSelfPermission() {
       val isEnabled  =  if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

           ContextCompat.checkSelfPermission(
                    context,
               Manifest.permission.POST_NOTIFICATIONS
           ) == PackageManager.PERMISSION_GRANTED
       }else true

       _state.update {
           it.copy(
               isEnabled = isEnabled
           )
       }
   }
    private fun openAppSettings(){
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", context.packageName , null)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)

    }

}