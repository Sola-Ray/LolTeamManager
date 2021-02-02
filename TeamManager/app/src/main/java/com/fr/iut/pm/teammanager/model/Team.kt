package com.fr.iut.pm.teammanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey

const val NEW_TEAM_ID = 0L

@Entity(tableName = "teams")
data class Team(val name: String = "",
           val toplaner: User? = null,
           val jungler: User? = null,
           val midlaner: User? = null,
           val botlaner: User? = null,
           val support: User? = null,
           @PrimaryKey(autoGenerate = true) val id: Long = NEW_TEAM_ID) {
    override fun toString(): String {
        return "$id : $name : $toplaner : $jungler : $midlaner : $botlaner : $support"
    }
}