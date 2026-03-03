package com.vs.february2026_challenge.stillConnected

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import androidx.core.content.getSystemService
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first

class ConnectionService(
    private val context: Context
) : ConnectivityProvider {
    val connectivityManager = context.getSystemService<ConnectivityManager>()!!
   private val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()

    private fun isAirplaneModeOn(): Boolean =
        android.provider.Settings.Global.getInt(
            context.contentResolver,
            android.provider.Settings.Global.AIRPLANE_MODE_ON, 0
        ) != 0
    override val connectivityState: Flow<ConnectionState>
        get() = callbackFlow {
            val networkCallback = object : ConnectivityManager.NetworkCallback(){
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    trySend(ConnectionState.CONNECTED)
                    Log.d("NetworkCallback", "onAvailable")
                }

                override fun onCapabilitiesChanged(
                    network: Network,
                    networkCapabilities: NetworkCapabilities
                ) {
                    super.onCapabilitiesChanged(network, networkCapabilities)
                    val connected = networkCapabilities.hasCapability(
                        NetworkCapabilities.NET_CAPABILITY_VALIDATED
                    )
                    if(connected) trySend(ConnectionState.CONNECTED)

                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    if(isAirplaneModeOn()){
                        trySend(ConnectionState.AIRPLANE_MODE_ACTIVE)
                        Log.d("NetworkCallback", "onAirplaceActive")
                    }else{
                        trySend(ConnectionState.CONNECTION_LOST)
                        Log.d("NetworkCallback", "onLost")
                    }

                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    trySend(ConnectionState.AIRPLANE_MODE_ACTIVE)
                    Log.d("NetworkCallback", "onUnavailable")

                }
            }
            connectivityManager.requestNetwork(networkRequest, networkCallback)

            awaitClose {
                connectivityManager.unregisterNetworkCallback(networkCallback)
            }

        }
}