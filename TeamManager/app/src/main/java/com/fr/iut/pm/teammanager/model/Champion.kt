package com.fr.iut.pm.teammanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "champion")
class Champion(
    @PrimaryKey val id: Int,
    val imageLink: String?) : Comparable<Champion>{
    override fun toString(): String {
        return "$id : ($imageLink)"
    }

    override fun compareTo(other: Champion): Int {
        return imageLink!!.compareTo(other.imageLink!!)
    }
}