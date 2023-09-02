package com.jeeldesai.android.feedyou.util.extensions

import com.jeeldesai.android.feedyou.data.model.entry.Entry
import com.jeeldesai.android.feedyou.data.model.entry.EntryLight

fun List<Entry>.sortedByDate() = this.sortedByDescending { it.date }

@JvmName("sortedByDateEntryLight")
fun List<EntryLight>.sortedByDate() = this.sortedByDescending { it.date }

fun List<EntryLight>.sortedUnreadOnTop() = this.sortedByDate().sortedBy { it.isRead }