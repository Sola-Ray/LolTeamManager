package com.fr.iut.pm.teammanager.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.fr.iut.pm.teammanager.R
import com.fr.iut.pm.teammanager.fragment.TeamFragment
import com.fr.iut.pm.teammanager.fragment.TeamListFragment
import com.fr.iut.pm.teammanager.model.NEW_TEAM_ID

class TeamListActivity : AppCompatActivity(),
    TeamListFragment.OnInteractionListener, TeamFragment.OnInteractionListener {

    companion object {
        fun createFragment() = TeamListFragment()
        const val TEAM_LIST_FRAGMENT = "TeamListFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val teamBtn = findViewById<Button>(R.id.btn_teams)

        teamBtn.setOnClickListener{
            teamButtonClicked()
        }

        if(supportFragmentManager.findFragmentById(R.id.container) == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, createFragment())
                .commit()
        }
    }

    override fun onTeamSelected(teamId: Long) {
        startActivity(TeamActivity.getIntent(this, teamId))
    }

    override fun onAddNewTeam() = startActivity(TeamActivity.getIntent(this, NEW_TEAM_ID))

    private fun removeDisplayedFragment() {
        supportFragmentManager.findFragmentById(R.id.container)?.let {
            supportFragmentManager.beginTransaction().remove(it).commit()
        }
    }

    private fun teamButtonClicked(){
        if(supportFragmentManager.findFragmentByTag(TEAM_LIST_FRAGMENT) == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, createFragment())
                .commitNow()
        }
    }

    override fun onTeamSaved() {
    }

    override fun onTeamDeleted() {
        finish()
    }

}
