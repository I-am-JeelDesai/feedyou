package com.jeeldesai.android.feedyou.data.model.cross

import com.jeeldesai.android.feedyou.data.model.entry.EntryToggleable

data class FeedTitleWithEntriesToggleable(
    val feedTitle: String,
    val entriesToggleable: List<EntryToggleable>
)