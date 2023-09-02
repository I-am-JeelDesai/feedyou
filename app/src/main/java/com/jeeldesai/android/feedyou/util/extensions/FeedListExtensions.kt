package com.jeeldesai.android.feedyou.util.extensions

import com.jeeldesai.android.feedyou.data.model.feed.FeedLight
import com.jeeldesai.android.feedyou.data.model.feed.FeedManageable

@JvmName("sortedByTitleFeedLight")
fun List<FeedLight>.sortedByTitle() = this.sortedBy { it.title }

fun List<FeedLight>.sortedByUnreadCount() = this.sortedByDescending { it.unreadCount }

fun List<FeedManageable>.sortedByTitle() = this.sortedBy { it.title }

fun List<FeedManageable>.sortedByCategory() = this.sortedBy { it.category }