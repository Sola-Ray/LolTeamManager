package com.fr.iut.pm.teammanager.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.fr.iut.pm.teammanager.R

abstract class SimpleFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        //setContentView()

        if(supportFragmentManager.findFragmentById(R.id.container) == null) {
            supportFragmentManager.beginTransaction().add(R.id.container, createFragment()).commit()
        }
    }

    protected abstract fun createFragment(): Fragment

}