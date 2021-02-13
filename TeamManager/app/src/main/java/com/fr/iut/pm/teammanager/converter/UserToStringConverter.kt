package com.fr.iut.pm.teammanager.converter

import androidx.databinding.ObservableField
import androidx.room.TypeConverter
import com.fr.iut.pm.teammanager.model.User

class UserToStringConverter {

    @TypeConverter
    fun fromUser(user: User?) = "${user?.username},${user?.profileIconId},${user?.summonerId},${user?.accountId},${user?.userId}"

    @TypeConverter
    fun toUser(user: String): User {
        val s = user.split(",")
        return User(s[0], s[1].toInt(), s[2], s[3], s[4].toLong())
    }
}
