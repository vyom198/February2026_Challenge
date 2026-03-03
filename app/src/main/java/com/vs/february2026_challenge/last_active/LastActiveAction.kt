package com.vs.february2026_challenge.last_active

sealed interface LastActiveAction {
  data object onStore : LastActiveAction
}