package com.alamin.newsapp.ui.navigation

import kotlinx.serialization.Serializable

sealed class Destination {
    @Serializable
    data object Home: Destination()

    @Serializable
    data class Details(val article: String): Destination()
}