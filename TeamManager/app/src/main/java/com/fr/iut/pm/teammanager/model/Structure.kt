package com.fr.iut.pm.teammanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey

const val NEW_STRUCT_ID = 0L

@Entity(tableName = "structures")
class Structure(var name: String,
                @PrimaryKey(autoGenerate = true)
                val structId: Long = NEW_STRUCT_ID) {
}