package com.jeeldesai.android.feedyou.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeeldesai.android.feedyou.data.FeedYouRepository
import com.jeeldesai.android.feedyou.data.model.cross.FeedWithEntries
import com.jeeldesai.android.feedyou.data.remote.FeedFetcher
import kotlinx.coroutines.launch

abstract class FeedAddingViewModel: ViewModel() {

    val repo = FeedYouRepository.get()
    private val fetcher = FeedFetcher()

    val feedRequestLiveData = fetcher.feedWithEntriesLive
    var currentFeedIds = listOf<String>()

    var isActiveRequest = false
    var requestFailedNoticeEnabled = false
    var alreadyAddedNoticeEnabled = false
    var subscriptionLimitNoticeEnabled = false
    var lastInputUrl = ""

    fun requestFeed(url: String, backup: String? = null) {
        onFeedRequested()
        viewModelScope.launch {
            fetcher.request(url)
        }
    }

    private fun onFeedRequested() {
        isActiveRequest = true
        requestFailedNoticeEnabled = true
        alreadyAddedNoticeEnabled = true
        subscriptionLimitNoticeEnabled = true
    }

    fun addFeedWithEntries(feedWithEntries: FeedWithEntries) {
        repo.addFeedWithEntries(feedWithEntries)
    }

    fun cancelRequest() {
        fetcher.cancel()
    }
}