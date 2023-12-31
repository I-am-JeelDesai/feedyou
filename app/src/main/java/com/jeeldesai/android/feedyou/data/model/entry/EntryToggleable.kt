package com.jeeldesai.android.feedyou.data.model.entry

// Entry data with only fields toggleable by user
data class EntryToggleable(
    val url: String,
    val isStarred: Boolean,
    val isRead: Boolean,
)