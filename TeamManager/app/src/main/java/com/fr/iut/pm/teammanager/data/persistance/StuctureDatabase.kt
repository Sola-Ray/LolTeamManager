package com.fr.iut.pm.teammanager.data.persistance

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fr.iut.pm.teammanager.TeamApplication
import com.fr.iut.pm.teammanager.data.persistance.dao.StructureDAO
import com.fr.iut.pm.teammanager.model.Structure

@Database(entities = [Structure::class], version = 1)
abstract class StuctureDatabase : RoomDatabase() {

    abstract fun structDAO(): StructureDAO

    companion object {
        private lateinit var application: Application
        @Volatile
        private var instance: StuctureDatabase? = null

        private fun getInstance(): StuctureDatabase {
            if (::application.isInitialized) {
                if (instance == null)
                    synchronized(this) {
                        if (instance == null)
                            instance = Room.inMemoryDatabaseBuilder(
                                application.applicationContext,
                                StuctureDatabase::class.java)
                                .allowMainThreadQueries()
                                .build()
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
            getInstance().structDAO().apply {
                insert(Structure("Test"))
            }
        }
    }
}
