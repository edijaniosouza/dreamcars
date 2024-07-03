package com.barrosedijanio.dreamcars.core.worker

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.work.BackoffPolicy
import androidx.work.CoroutineWorker
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkerParameters
import com.barrosedijanio.dreamcars.core.generic.GenericResult
import com.barrosedijanio.dreamcars.repositories.ServiceRepository
import java.time.Duration
import java.util.concurrent.TimeUnit

const val UPLOAD_LEADS = "upload_leads"

class UploadFavCars(
    context: Context,
    workerParameters: WorkerParameters,
    private val serviceRepository: ServiceRepository
) :
    CoroutineWorker(context, workerParameters) {

    @SuppressLint("RestrictedApi")
    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    override suspend fun doWork(): Result {
        val result = serviceRepository.postLeads()
        return when(result){
            GenericResult.Success -> Result.Success()
            is GenericResult.Error -> Result.Retry()
            else -> Result.Failure()
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
val uploadWorkRequest = PeriodicWorkRequestBuilder<UploadFavCars>(
    repeatInterval = 15,
    repeatIntervalTimeUnit = TimeUnit.MINUTES
).setBackoffCriteria(
    backoffPolicy = BackoffPolicy.LINEAR,
    duration = Duration.ofMinutes(5)
).build()
