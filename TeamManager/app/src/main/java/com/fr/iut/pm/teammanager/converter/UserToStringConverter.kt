package com.fr.iut.pm.teammanager.converter

import androidx.room.TypeConverter
import com.fr.iut.pm.teammanager.model.User

class UserToStringConverter {

    @TypeConverter
    fun fromUser(user: User) = "${user.username},${user.id}"

    @TypeConverter
    fun toUser(user: String): User {
        val s = user.split(",")
        return User(s[0], s[1].toLong())
    }
}
