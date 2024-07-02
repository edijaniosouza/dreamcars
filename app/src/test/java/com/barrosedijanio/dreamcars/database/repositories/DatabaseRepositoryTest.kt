package com.barrosedijanio.dreamcars.database.repositories

import android.util.Log
import com.barrosedijanio.dreamcars.MainDispatcherRule
import com.barrosedijanio.dreamcars.R
import com.barrosedijanio.dreamcars.core.generic.GenericResult
import com.barrosedijanio.dreamcars.database.dao.CarsDao
import com.barrosedijanio.dreamcars.database.dao.FavoriteCarsDao
import com.barrosedijanio.dreamcars.database.dao.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DatabaseRepositoryTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var userDao: UserDao

    @Mock
    private lateinit var carsDao: CarsDao

    @Mock
    private lateinit var favoriteCarsDao: FavoriteCarsDao

    private lateinit var databaseRepository: DatabaseRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        databaseRepository = DatabaseRepository(userDao, carsDao, favoriteCarsDao)
    }

    private val email = "teste@teste.com"
    private val username = "teste"

    @Test
    fun create_user_returns_success() = runTest{
        launch {
            val testResult = databaseRepository.signUp(email = email, userName = username)
            print("UE -> $testResult")
            Assertions.assertEquals(GenericResult.Success, GenericResult.Empty)
        }
    }

    @Test
    fun login_user_with_username_returns_success_when_user_exists() = runTest {
        launch {
            val testResult = databaseRepository.loginWithUsername(username).first()
            Assertions.assertEquals(GenericResult.Success, testResult)
        }
    }

    @Test
    fun login_user_with_username_returns_error_when_user_dont_exists() {
        val nonUser = "testeUser"
        CoroutineScope(Dispatchers.Main).launch {
            val testResult = databaseRepository.loginWithUsername(nonUser).first()
            Assertions.assertEquals(GenericResult.Error(R.string.user_not_found), testResult)
        }
    }
}