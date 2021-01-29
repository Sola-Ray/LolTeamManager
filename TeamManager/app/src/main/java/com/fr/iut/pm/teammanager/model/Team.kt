package com.fr.iut.pm.teammanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey

const val NEW_TEAM_ID = 0L

@Entity(tableName = "teams")
class Team(@PrimaryKey(autoGenerate = true) val id: Long = NEW_TEAM_ID,
           var name: String = "",
           var toplaner: User? = null,
           var jungler: User? = null,
           var midlaner: User? = null,
           var botlaner: User? = null,
           var support: User? = null) {
    override fun toString(): String {
        return "$id : $name : $toplaner : $jungler : $midlaner : $botlaner : $support"
    }
}