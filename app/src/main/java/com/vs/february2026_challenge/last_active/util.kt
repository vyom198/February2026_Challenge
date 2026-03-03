package com.vs.february2026_challenge.last_active

import android.text.format.DateFormat
import java.util.Calendar


fun formatActivityStatus(timestamp: Long?): String {
    if (timestamp == null || timestamp == 0L) {
        return "No activity yet"
    }

    val now = Calendar.getInstance()
    val lastActive = Calendar.getInstance().apply { timeInMillis = timestamp }


    val isToday = now.get(Calendar.YEAR) == lastActive.get(Calendar.YEAR) &&
            now.get(Calendar.DAY_OF_YEAR) == lastActive.get(Calendar.DAY_OF_YEAR)


    val yesterday = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, -1) }
    val isYesterday = yesterday.get(Calendar.YEAR) == lastActive.get(Calendar.YEAR) &&
            yesterday.get(Calendar.DAY_OF_YEAR) == lastActive.get(Calendar.DAY_OF_YEAR)

    return when {
        isToday -> {

            val time = DateFormat.format("HH:mm", lastActive)
            "Last active at $time"
        }
        isYesterday -> {
            // "Last active yesterday" [cite: 42]
            "Last active yesterday"
        }
        else -> {
            // "Last active on MMM dd" [cite: 43]
            val date = DateFormat.format("MMM dd", lastActive)
            "Last active on $date"
        }
    }
}