package com.jeeldesai.android.feedyou.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jeeldesai.android.feedyou.data.local.database.dao.CombinedDao
import com.jeeldesai.android.feedyou.data.model.entry.Entry
import com.jeeldesai.android.feedyou.data.model.feed.Feed
import com.jeeldesai.android.feedyou.data.model.cross.FeedEntryCrossRef

@Database(
    entities = [
        Feed::class,
        Entry::class,
        FeedEntryCrossRef::class
    ],
    version = 1
)
@TypeConverters(DateConverters::class)
abstract class FeedYouDatabase : RoomDatabase() {

    abstract fun combinedDao(): CombinedDao

    companion object {

        private const val NAME = "database"

        fun build(context: Context): FeedYouDatabase {
            return Room
                .databaseBuilder(context, FeedYouDatabase::class.java, NAME)
                .build()
        }
    }
}