package com.fr.iut.pm.teammanager.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.fr.iut.pm.teammanager.R
import com.fr.iut.pm.teammanager.fragment.MatchListFragment

class MatchListActivity : AppCompatActivity() {

    companion object {
        fun createFragment() = MatchListFragment()
        fun getIntent(context: Context) =
            Intent(context, MatchListActivity::class.java).apply {
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val btnTeams: Button = findViewById(R.id.btn_teams)
        btnTeams.setOnClickListener {
            onBackPressed()
        }

        if(supportFragmentManager.findFragmentById(R.id.container) == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, createFragment())
                .commit()
        }
    }
}
