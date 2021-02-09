package com.fr.iut.pm.teammanager.data.persistance

import android.app.Application
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fr.iut.pm.teammanager.TeamApplication
import com.fr.iut.pm.teammanager.converter.UserToStringConverter
import com.fr.iut.pm.teammanager.data.persistance.dao.TeamDAO
import com.fr.iut.pm.teammanager.model.Team
import com.fr.iut.pm.teammanager.model.User

@Database(entities = [Team::class], version = 1)
@TypeConverters(UserToStringConverter::class)
abstract class TeamDatabase : RoomDatabase() {

    abstract fun teamDAO(): TeamDAO

    companion object {
        private lateinit var application: Application

        @Volatile
        private var instance: TeamDatabase? = null

        fun getInstance(): TeamDatabase {
            if (::application.isInitialized) {
                if (instance == null)
                    synchronized(this) {
                        if (instance == null) {
                            instance = Room.inMemoryDatabaseBuilder(
                                application.applicationContext,
                                TeamDatabase::class.java)
                                .allowMainThreadQueries()
                                .build()
                        }
                        dirtyPopulateDB()
                    }
                return instance!!
            } else
                throw RuntimeException("the database must be first initialized")
        }


        @Synchronized
        fun initialize(app: TeamApplication) {
            if (::application.isInitialized)
                throw RuntimeException("the database must not be initialized twice")

            application = app
        }

        private fun dirtyPopulateDB() {
            getInstance().teamDAO().apply {
                insert(Team ("Abusing Mid Gap", User("Minyan Chan", 0, "1", "1"),
                    User("Silvesster", 0, "0", "0"),
                    User("Soła", 0, "0", "0"),
                    User("Rayon Alcool", 0, "0", "0"),
                    User("Timoη", 0, "0", "0")))
            }
        }
    }
}
