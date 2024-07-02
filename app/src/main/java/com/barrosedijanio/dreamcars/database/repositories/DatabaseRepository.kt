package com.barrosedijanio.dreamcars.database.repositories

import com.barrosedijanio.dreamcars.R
import com.barrosedijanio.dreamcars.core.generic.GenericResult
import com.barrosedijanio.dreamcars.database.dao.CarsDao
import com.barrosedijanio.dreamcars.database.dao.FavoriteCarsDao
import com.barrosedijanio.dreamcars.database.dao.UserDao
import com.barrosedijanio.dreamcars.database.model.FavoriteCar
import com.barrosedijanio.dreamcars.database.model.User
import com.barrosedijanio.dreamcars.navigation.Session
import com.barrosedijanio.dreamcars.service.model.Car
import com.barrosedijanio.dreamcars.service.model.Cars
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import java.sql.SQLException

class DatabaseRepository(
    private val userDao: UserDao,
    private val carsDao: CarsDao,
    private val favoriteCarsDao: FavoriteCarsDao,
    private val session: Session
) {


    suspend fun loginWithUsername(userName: String): Flow<GenericResult> = flow {
        try {
            userDao.loginWithUserName(userName).collect { user ->
                if (user != null) {
                    session.setUserId(user.id.toInt())
                    emit(GenericResult.Success)
                } else {
                    emit(GenericResult.Error(R.string.user_not_found))
                }
            }
        } catch (e: Exception) {
            emit(GenericResult.Error(R.string.error_on_searching_user))
        }
    }

    suspend fun loginWithEmail(email: String): Flow<GenericResult> = flow {
        try {
            userDao.loginWithEmail(email).collect { user ->
                if (user != null) {
                    session.setUserId(user.id.toInt())
                    emit(GenericResult.Success)
                } else {
                    emit(GenericResult.Error(R.string.user_not_found))
                }
            }
        } catch (e: Exception) {
            emit(GenericResult.Error(R.string.error_on_searching_user))
        }
    }

    suspend fun signUp(email: String, userName: String): GenericResult {
        try {
            val hasUserName = userDao.loginWithUserName(userName).first()
            val hasUserEmail = userDao.loginWithEmail(email).first()

            if (hasUserName != null || hasUserEmail != null) {
                return GenericResult.Error(R.string.user_already_exists)
            }

            val newUser: Long = userDao.insertUser(
                User(
                    userName = userName,
                    email = email
                )
            )
            session.setUserId(newUser.toInt())
            return GenericResult.Success
        } catch (exception: SQLException) {
            return GenericResult.Error(R.string.error_on_sign_up)
        } catch (e: Exception) {
            return GenericResult.Error(R.string.database_error)
        }
    }


    // Listagem e inserçao de novos carros

    fun getCarData(): Flow<List<Car>> {
        return try {
            carsDao.getCars()
        } catch (e: Exception) {
            flow { emit(emptyList()) }
        }
    }

    suspend fun insertCarData(car: Cars): GenericResult {
        try {
            carsDao.insertManyCars(car.cars)
            return GenericResult.Success
        } catch (exception: SQLException) {
            return GenericResult.Error(R.string.error_registering_new_car)
        } catch (e: Exception) {
            return GenericResult.Error(R.string.database_error)
        }
    }

    // Carros favoritados
    suspend fun getFavoriteCars(): Flow<List<FavoriteCar>> {
        return try {
            favoriteCarsDao.getFavorites(session.getUserId().first())
        } catch (e: Exception) {
            flow { emit(emptyList()) }
        }
    }

    suspend fun addFavoriteCar(liked: FavoriteCar): GenericResult {
        try {
            favoriteCarsDao.insertFavoriteCar(liked)
            return GenericResult.Success
        } catch (exception: SQLException) {
            return GenericResult.Error(R.string.error_registering_new_car)
        } catch (e: Exception) {
            return GenericResult.Error(R.string.database_error)
        }
    }

    suspend fun deleteFavoriteCar(disliked: Int): GenericResult {
        try {
            val carId = favoriteCarsDao.getFavoriteCar(disliked).first()
            carId?.let { car ->
                favoriteCarsDao.deleteFavoriteCar(car)
            }
            return GenericResult.Success
        } catch (exception: SQLException) {
            return GenericResult.Error(R.string.error_deleting_a_car)
        } catch (e: Exception) {
            return GenericResult.Error(R.string.database_error)
        }
    }

    suspend fun insertCar(car: Car): GenericResult {
        try {
            carsDao.insertCar(car)
            return GenericResult.Success
        } catch (exception: SQLException) {
            return GenericResult.Error(R.string.error_registering_new_car)
        } catch (e: Exception) {
            return GenericResult.Error(R.string.database_error)
        }
    }

}