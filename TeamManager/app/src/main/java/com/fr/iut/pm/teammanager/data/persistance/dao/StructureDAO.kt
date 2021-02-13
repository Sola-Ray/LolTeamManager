package com.fr.iut.pm.teammanager.data.persistance.dao

import androidx.room.*
import com.fr.iut.pm.teammanager.model.Structure

@Dao
interface StructureDAO {
    @Query("SELECT * FROM structures")
    fun getAll(): List<Structure>

    @Query("SELECT * FROM structures WHERE structId = :id")
    fun findById(id: Long): Structure

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(structure: Structure)

    @Insert
    fun insertAll(vararg structures: Structure)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(structure: Structure)

    @Delete
    fun delete(structure: Structure)
}