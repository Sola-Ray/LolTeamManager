package com.fr.iut.pm.teammanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

const val NEW_USER_ID = 0L

@Entity(tableName = "users")
data class User(
    @field:Json(name = "name")
    val username: String,
    @field:Json(name = "profileIconId")
    val profileIconId: Int?,
    @field:Json(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Long = NEW_USER_ID) {
    override fun toString(): String {
        return "$id : $username"
    }
}