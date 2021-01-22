package com.fr.iut.pm.teammanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "champion")
class Champion (@PrimaryKey val id: Int,
                val name: String,
                val imageLink: String?){
    override fun toString(): String {
        return "$id : $name : ($imageLink)"
    }
}