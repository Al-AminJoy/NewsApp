package com.alamin.newsapp.domain.model

enum class NewsCategory(val apiValue: String, val displayName: String) {
    ALL("", "All"),
    GENERAL("general", "General"),
    BUSINESS("business", "Business"),
    ENTERTAINMENT("entertainment", "Entertainment"),
    HEALTH("health", "Health"),
    SCIENCE("science", "Science"),
    SPORTS("sports", "Sports"),
    TECHNOLOGY("technology", "Technology");

    companion object{
        fun default() = ALL
    }
}