package com.fr.iut.pm.teammanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey

const val NEW_USER_ID = 0L

@Entity(tableName = "users")
data class User(val username: String, @PrimaryKey(autoGenerate = true) val id: Long = NEW_USER_ID) {
    override fun toString(): String {
        return "$id : $username"
    }
}