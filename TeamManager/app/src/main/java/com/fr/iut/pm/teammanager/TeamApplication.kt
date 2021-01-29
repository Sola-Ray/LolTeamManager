package com.fr.iut.pm.teammanager

import android.app.Application
import com.fr.iut.pm.teammanager.data.persistance.TeamDatabase
import com.fr.iut.pm.teammanager.data.persistance.UserDatabase

class TeamApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        TeamDatabase.initialize(this)
    }
}