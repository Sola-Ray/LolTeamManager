package com.fr.iut.pm.teammanager.data.persistance.dao

import androidx.room.*
import com.fr.iut.pm.teammanager.model.Team
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface TeamDAO {
    @Query("SELECT * FROM teams")
    fun getAll(): List<Team>

    @Query("SELECT * FROM teams WHERE id = :id")
    fun findById(id: Long): Team

    @Insert(onConflict = REPLACE)
    fun insert(team: Team)

    @Insert
    fun insertAll(vararg teams: Team)

    @Update(onConflict = REPLACE)
    fun update(team: Team)

    @Delete
    fun delete(team: Team)
}