package com.barrosedijanio.dreamcars.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.barrosedijanio.dreamcars.database.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User) : Long

    @Query("SELECT * FROM users WHERE userName = :userName")
    fun loginWithUserName(userName: String): Flow<User?>

    @Query("SELECT * FROM users WHERE email = :email")
    fun loginWithEmail(email: String) : Flow<User?>
}