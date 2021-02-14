package com.fr.iut.pm.teammanager.activity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.fr.iut.pm.teammanager.R
import com.fr.iut.pm.teammanager.fragment.MatchListFragment
import com.fr.iut.pm.teammanager.fragment.TeamFragment
import com.fr.iut.pm.teammanager.fragment.TeamListFragment
import com.fr.iut.pm.teammanager.model.NEW_TEAM_ID

private const val match_list_fragment = "matchListFragment"

class TeamListActivity : AppCompatActivity(),
    TeamListFragment.OnInteractionListener, TeamFragment.OnInteractionListener {

    companion object {
        fun createFragment() = TeamListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        createNotificationChannel()

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

    override fun onTeamSaved() {
    }

    override fun onTeamDeleted() {
        finish()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channelId = "myChannel"
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun openMatchHistory(view:View) {
        startActivity(MatchListActivity.getIntent(this))
    }
}
