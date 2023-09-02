package com.jeeldesai.android.feedyou.data.model

data class UpdateValues(
    var added: Int = 0,
    var updated: Int = 0
) {

    fun isEmpty(): Boolean {
        return added + updated == 0
    }

    fun clear() {
        added = 0
        updated = 0
    }
}