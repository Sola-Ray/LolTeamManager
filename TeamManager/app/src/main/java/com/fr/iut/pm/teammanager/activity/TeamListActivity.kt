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
    TeamListFragment.OnInteractionListener {

    private fun createFragment() = TeamListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val teamBtn = findViewById<Button>(R.id.btn_teams)

        Log.d("TEST", "onCreate: LE MAIN")

        teamBtn.setOnClickListener{
            Log.d("TEST", "onCreate: BTN TEAM CLICKED")
            teamButtonClicked()
        }


        if(supportFragmentManager.findFragmentById(R.id.container) == null) {
            Log.d("TEST", "onCreate: frag")
            supportFragmentManager.beginTransaction()
                .add(R.id.container, createFragment())
                .commit()
        }
    }

    override fun onTeamSelected(teamId: Long) {
        //if (isTwoPane) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, TeamFragment.newInstance(teamId))
                    .commit()
        /*} else {
            startActivity(TeamActivity.getIntent(this, teamId))
        }*/
    }

    override fun onAddNewTeam() = startActivity(TeamActivity.getIntent(this, NEW_TEAM_ID))

    private fun removeDisplayedFragment() {
        supportFragmentManager.findFragmentById(R.id.container)?.let {
            supportFragmentManager.beginTransaction().remove(it).commit()
        }
    }

    private fun teamButtonClicked(){
        /*if(supportFragmentManager.findFragmentByTag(team_list_fragment) == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, createFragment())
                .commitNow()
        }*/
    }

    /*override fun onTeamDeleted() {
        if (isTwoPane) {
            removeDisplayedFragment()
        } else
            finish()
    }*/

}
