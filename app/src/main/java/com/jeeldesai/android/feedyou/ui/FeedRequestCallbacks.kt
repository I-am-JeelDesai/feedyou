package com.jeeldesai.android.feedyou.ui

interface FeedRequestCallbacks {

    fun onRequestSubmitted(url: String, backup: String? = null)

    fun onRequestDismissed()
}