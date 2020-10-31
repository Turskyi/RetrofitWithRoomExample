package ua.turskyi.retrofitwithroomexample.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface VideoDao {
    @Query("select * from videoentity")
    fun getVideos(): LiveData<List<VideoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg videoEntities: VideoEntity)
}

@Database(entities = [VideoEntity::class], version = 1)
abstract class VideosDatabase : RoomDatabase() {
    abstract val videoDao: VideoDao
}

private lateinit var INSTANCE: VideosDatabase

fun getDatabase(context: Context): VideosDatabase {

    /* in case of migration */
//    val migrationFrom1To2: Migration = object : Migration(1, 2) {
//        override fun migrate(database: SupportSQLiteDatabase) {
//            database.execSQL("ALTER TABLE videoentity ADD COLUMN birthday INTEGER DEFAULT 0 NOT NULL")
//        }
//    }
    synchronized(VideosDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                VideosDatabase::class.java,
                "videos"
            )
                /* in case of migration */
//                .addMigrations(migrationFrom1To2)
                .build()
        }
    }
    return INSTANCE
}
