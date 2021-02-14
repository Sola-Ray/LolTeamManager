package com.fr.iut.pm.teammanager.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.fr.iut.pm.teammanager.R
import com.fr.iut.pm.teammanager.fragment.TeamFragment
import com.fr.iut.pm.teammanager.model.NEW_TEAM_ID

class TeamActivity : AppCompatActivity(), TeamFragment.OnInteractionListener {

    companion object {
        private const val MY_TEAM_ID = "my_team_id"

        fun getIntent(context: Context, teamId: Long) =
            Intent(context, TeamActivity::class.java).apply {
                putExtra(MY_TEAM_ID, teamId)
            }
    }

    private var teamId = NEW_TEAM_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        teamId = intent.getLongExtra(MY_TEAM_ID, NEW_TEAM_ID)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val teamBtn = findViewById<Button>(R.id.btn_teams)

        teamBtn.setOnClickListener{
            onBackPressed()
        }

        if(supportFragmentManager.findFragmentById(R.id.container) == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, createFragment())
                .commitNow()
        }
    }

    private fun createFragment() = TeamFragment.newInstance(teamId)

    override fun onTeamSaved() = finish()

    override fun onTeamDeleted() = finish()
}