package com.fr.iut.pm.teammanager.data.persistance

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fr.iut.pm.teammanager.UserApplication
import com.fr.iut.pm.teammanager.model.User
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDAO(): UserDAO

    companion object {
        private lateinit var application: Application
        @Volatile
        private var instance: UserDatabase? = null

        fun getInstance(): UserDatabase {
            if (::application.isInitialized) {
                if (instance == null)
                    synchronized(this) {
                        if (instance == null)
                            instance = Room.inMemoryDatabaseBuilder(
                                application.applicationContext,
                                UserDatabase::class.java)
                                .allowMainThreadQueries()
                                .build()
                        dirtyPopulateDB()
                    }
                return instance!!
            } else
                throw RuntimeException("the database must be first initialized")
        }


        @Synchronized
        fun initialize(app: UserApplication) {
            if (::application.isInitialized)
                throw RuntimeException("the database must not be initialized twice")

                application = app
        }

        private fun dirtyPopulateDB() {
            getInstance().userDAO().apply {
                insert(User("Test", 1234))
            }
        }
    }
}
