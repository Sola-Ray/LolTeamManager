package com.fr.iut.pm.teammanager

import android.app.Application
import com.fr.iut.pm.teammanager.data.persistance.UserDatabase

class UserApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        UserDatabase.initialize(this)
    }
}