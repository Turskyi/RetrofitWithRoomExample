package ua.turskyi.retrofitwithroomexample.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import ua.turskyi.retrofitwithroomexample.database.getDatabase
import ua.turskyi.retrofitwithroomexample.repository.VideosRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters):
        CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    /**
     * A coroutine-friendly method to do your work.
     */
    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = VideosRepository(database)
        return try {
            repository.refreshVideos()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}
