package com.jeeldesai.android.feedyou.ui.viewmodel

import androidx.lifecycle.*
import com.jeeldesai.android.feedyou.data.FeedYouRepository
import com.jeeldesai.android.feedyou.data.local.FeedPreferences
import com.jeeldesai.android.feedyou.data.model.entry.Entry
import com.jeeldesai.android.feedyou.data.remote.FeedFetcher
import com.jeeldesai.android.feedyou.util.EntryToHtmlUtil

class EntryViewModel : ViewModel() {

    private val repo = FeedYouRepository.get()

    private val entryIdLiveData = MutableLiveData<String>()
    private val entryLiveData = Transformations
        .switchMap(entryIdLiveData) { entryId ->
            repo.getEntry(entryId)
        }

    private val _htmlLiveData = MediatorLiveData<String?>()
    val htmlLiveData: LiveData<String?> get() = _htmlLiveData

    var lastPosition: Pair<Int, Int> = Pair(0, 0)
    val textSize get() = FeedPreferences.textSize
    val font get() = FeedPreferences.font
    val isBannerEnabled get() = FeedPreferences.isBannerEnabled
    var isInitialLoading = true
    val entry: Entry? get() = entryLiveData.value
    private var isExcerpt = false // As of now, unused

    init {
        _htmlLiveData.addSource(entryLiveData) { source ->
            if (source != null) {
                isExcerpt = source.content?.startsWith(FeedFetcher.FLAG_EXCERPT) ?: false
                updateHtml(source)
            } else {
                _htmlLiveData.value = null
            }
        }
    }

    fun getEntryById(entryId: String) {
        entryIdLiveData.value = entryId
    }

    fun setTextSize(textSize: Int) {
        FeedPreferences.textSize = textSize
        entry?.let { updateHtml(it) }
    }

    private fun updateHtml(entry: Entry) {
        _htmlLiveData.value = EntryToHtmlUtil
            .setFontSize(textSize)
            .setFontFamily(font)
            .setShouldIncludeHeader(!isBannerEnabled)
            .format(entry.toMinimal())
    }

    fun saveChanges() {
        entry?.let { repo.updateEntryAndFeedUnreadCount(it.url, true, it.isStarred) }
    }
}