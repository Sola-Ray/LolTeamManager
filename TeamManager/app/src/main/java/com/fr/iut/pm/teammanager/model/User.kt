package com.fr.iut.pm.teammanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

const val NEW_USER_ID = 0L

@Entity(tableName = "users")
data class User(
    @field:Json(name = "name")
    var username: String,
    @field:Json(name = "profileIconId")
    var profileIconId: Int?,
    @field:Json(name = "accountId")
    var accountId: String?,
    @field:Json(name = "id")
    var summonerId: String?,
    @PrimaryKey(autoGenerate = true)
    var userId: Long = NEW_USER_ID) {

    override fun toString(): String {
        return "$userId : $username : $profileIconId"
    }
}