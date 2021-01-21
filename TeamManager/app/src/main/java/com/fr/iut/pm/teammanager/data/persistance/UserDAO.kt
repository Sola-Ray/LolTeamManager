package com.fr.iut.pm.teammanager.data.persistance

import androidx.room.*
import com.fr.iut.pm.teammanager.model.User

@Dao
interface UserDAO {
    @Query("SELECT * FROM users")
    fun getAll(): List<User>

    @Query("SELECT * FROM users WHERE id = :id")
    fun findById(id: Long): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dog: User)

    @Insert
    fun insertAll(vararg users: User)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(user: User)

    @Delete
    fun delete(user: User)
}