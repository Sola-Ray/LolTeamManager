package com.fr.iut.pm.teammanager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.fr.iut.pm.teammanager.data.persistance.TeamDatabase
import com.fr.iut.pm.teammanager.model.NEW_TEAM_ID
import com.fr.iut.pm.teammanager.model.Team

class TeamFragment : Fragment() {

    companion object {
        private const val MY_TEAM_ID = "my_team_id"

        fun newInstance(teamId: Long) = TeamFragment().apply {
            arguments = bundleOf(MY_TEAM_ID to teamId)
        }
    }

    private lateinit var team: Team
    private var teamId: Long = NEW_TEAM_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        teamId = savedInstanceState?.getLong(MY_TEAM_ID) ?: arguments?.getLong(MY_TEAM_ID) ?: NEW_TEAM_ID

        if(teamId == NEW_TEAM_ID) {
            requireActivity().setTitle("Ajout d'une équipe")
            team = Team()
        } else {
            team = TeamDatabase.getInstance().teamDAO().findById(teamId)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(MY_TEAM_ID, teamId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}