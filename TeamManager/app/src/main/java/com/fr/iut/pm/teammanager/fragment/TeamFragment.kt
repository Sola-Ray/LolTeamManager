package com.fr.iut.pm.teammanager.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.fr.iut.pm.teammanager.R
import com.fr.iut.pm.teammanager.data.persistance.TeamDatabase
import com.fr.iut.pm.teammanager.model.NEW_TEAM_ID
import com.fr.iut.pm.teammanager.model.Team
import kotlinx.android.synthetic.main.team_fragment.view.*

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
            requireActivity().title = getString(R.string.add_team)
            team = Team()
        } else {
            team = TeamDatabase.getInstance().teamDAO().findById(teamId)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(MY_TEAM_ID, teamId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.team_fragment, container, false)

        view.edit_team_name.setText(team.name)
        view.edit_toplaner.setText(team.toplaner?.username)
        view.edit_jgler.setText(team.jungler?.username)
        view.edit_midlaner.setText(team.midlaner?.username)
        view.edit_adc.setText(team.botlaner?.username)
        view.edit_support.setText(team.support?.username)
        return view
    }

    interface OnInteractionListener {
        fun onTeamSaved()
        fun onTeamDeleted()
    }

    private var listener: OnInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnInteractionListener")
        }
    }
}