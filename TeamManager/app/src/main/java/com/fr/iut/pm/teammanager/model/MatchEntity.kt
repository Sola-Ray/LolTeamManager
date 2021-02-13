package com.fr.iut.pm.teammanager.model

class MatchEntity(
    val isWinner: Boolean,
    val matchId: Long,
    val matchCreation: Long,
    val matchDuration: Long,
    val champId: Int,
    val kills: Int,
    val deaths: Int,
    val assists: Int,
    val gold: Int,
    val cs: Int,
    val champLevel: Int,
    val stats: LinkedHashMap<String, Int> = LinkedHashMap(),
    val items:Array<Int?> = arrayOfNulls<Int>(7),
    val sum1: String,
    val sum2: String,
    val champName: String,
    val typeMatch: String,
    val teamWinner: List<Int> = ArrayList(),
    val teamLoser: List<Int> = ArrayList(),
)