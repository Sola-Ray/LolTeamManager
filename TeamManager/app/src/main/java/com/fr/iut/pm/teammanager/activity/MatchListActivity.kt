package com.fr.iut.pm.teammanager.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.fr.iut.pm.teammanager.R
import com.fr.iut.pm.teammanager.fragment.MatchListFragment
import com.fr.iut.pm.teammanager.model.NEW_TEAM_ID

class MatchListActivity : AppCompatActivity() {

    companion object {
        private const val USER_ACCOUNT_ID = "user_account_id"
        private const val TEAM_ID = "team_id"
        fun getIntent(context: Context, userAccountId: String, teamId: Long) =
            Intent(context, MatchListActivity::class.java).apply {
                putExtra(USER_ACCOUNT_ID, userAccountId)
                putExtra(TEAM_ID, teamId)
            }
    }

    private var userAccountId = "0"
    private var teamId = NEW_TEAM_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        userAccountId = intent.getStringExtra(USER_ACCOUNT_ID).toString()
        teamId = intent.getLongExtra(TEAM_ID, NEW_TEAM_ID)

        val btnTeams: Button = findViewById(R.id.btn_teams)
        btnTeams.setOnClickListener {
            onBackPressed()
        }
        val btnHome: Button = findViewById(R.id.btn_home)
        btnHome.setOnClickListener {
            onBackPressed()
        }

        if(supportFragmentManager.findFragmentById(R.id.container) == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, createFragment())
                .commit()
        }
    }

    private fun createFragment() = MatchListFragment.newInstance(userAccountId, teamId)
}
