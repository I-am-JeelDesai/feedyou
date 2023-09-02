package com.jeeldesai.android.feedyou.data.local.database

import androidx.room.TypeConverter
import java.util.*

class DateConverters {

    @TypeConverter
    fun fromDate(date: Date?): Long? = date?.time

    @TypeConverter
    fun toDate(millisSinceEpoch: Long?): Date? = millisSinceEpoch?.let { Date(it) }
}