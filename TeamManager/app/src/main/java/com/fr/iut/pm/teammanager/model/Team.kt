package com.fr.iut.pm.teammanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey

const val NEW_TEAM_ID = 0L

@Entity(tableName = "teams")
data class Team(
    var name: String = "",
    var toplaner: User? = User("", 0, "", ""),
    var jungler: User? = User("", 0, "", ""),
    var midlaner: User? = User("", 0, "", ""),
    var botlaner: User? = User("", 0, "", ""),
    var support: User? = User("", 0, "", ""),
    @PrimaryKey(autoGenerate = true) val id: Long = NEW_TEAM_ID) {
    override fun toString(): String {
        return "$id : $name : $toplaner : $jungler : $midlaner : $botlaner : $support"
    }
}