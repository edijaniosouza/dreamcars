package com.barrosedijanio.dreamcars

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import com.barrosedijanio.dreamcars.navigation.Navigation
import com.barrosedijanio.dreamcars.ui.theme.DreamCarsTheme
import com.barrosedijanio.dreamcars.worker.UPLOAD_LEADS
import com.barrosedijanio.dreamcars.worker.uploadWorkRequest

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            DreamCarsTheme {
                LaunchedEffect(key1 = Unit) {
                    val workManager = WorkManager.getInstance(applicationContext)
                    workManager.enqueueUniquePeriodicWork(
                        UPLOAD_LEADS,
                        ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
                        uploadWorkRequest
                    )
                }

                Navigation()
            }
        }
    }
}