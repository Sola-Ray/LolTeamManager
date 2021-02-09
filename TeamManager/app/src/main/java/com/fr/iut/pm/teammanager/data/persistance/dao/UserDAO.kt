package com.fr.iut.pm.teammanager.data.persistance.dao

import androidx.room.*
import com.fr.iut.pm.teammanager.model.User

@Dao
interface UserDAO {
    @Query("SELECT * FROM users")
    fun getAll(): List<User>

    @Query("SELECT * FROM users WHERE userId = :id")
    fun findById(id: Long): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Insert
    fun insertAll(vararg users: User)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(user: User)

    @Delete
    fun delete(user: User)
}