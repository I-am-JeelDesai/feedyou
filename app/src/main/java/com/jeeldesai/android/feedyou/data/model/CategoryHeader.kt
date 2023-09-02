package com.jeeldesai.android.feedyou.data.model

data class CategoryHeader(
    val category: String,
    val isMinimized: Boolean,
    var unreadCount: Int = 0
)