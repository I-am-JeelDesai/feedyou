package com.jeeldesai.android.feedyou.data.remote.api

import com.google.gson.annotations.SerializedName
import com.jeeldesai.android.feedyou.data.model.SearchResultItem

class SearchResult {
    @SerializedName("results")
    lateinit var items: List<SearchResultItem>
}