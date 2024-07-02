package com.barrosedijanio.dreamcars

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.asIntState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import com.barrosedijanio.dreamcars.navigation.Navigation
import com.barrosedijanio.dreamcars.navigation.screens.Screens
import com.barrosedijanio.dreamcars.ui.theme.DreamCarsTheme
import com.barrosedijanio.dreamcars.ui.viewmodels.MainViewModel
import com.barrosedijanio.dreamcars.worker.UPLOAD_LEADS
import com.barrosedijanio.dreamcars.worker.uploadWorkRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                false
            }
        }

        actionBar?.hide()

        enableEdgeToEdge()
        setContent {
            val mainViewModel: MainViewModel = koinViewModel()
            val userLogged by mainViewModel.userSession.collectAsState()

            DreamCarsTheme {
                LaunchedEffect(key1 = Unit) {
                    val workManager = WorkManager.getInstance(applicationContext)
                    workManager.enqueueUniquePeriodicWork(
                        UPLOAD_LEADS,
                        ExistingPeriodicWorkPolicy.KEEP,
                        uploadWorkRequest
                    )
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(userLogged)
                }
            }
        }
    }
}