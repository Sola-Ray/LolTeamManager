package com.fr.iut.pm.teammanager.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.fr.iut.pm.teammanager.R
import com.fr.iut.pm.teammanager.activity.TeamActivity
import com.fr.iut.pm.teammanager.data.persistance.TeamDatabase
import com.fr.iut.pm.teammanager.data.persistance.TeamRepository
import com.fr.iut.pm.teammanager.data.persistance.dao.TeamDAO
import com.fr.iut.pm.teammanager.model.MatchEntity
import com.fr.iut.pm.teammanager.model.NEW_TEAM_ID
import com.fr.iut.pm.teammanager.model.Team
import com.fr.iut.pm.teammanager.model.User
import com.fr.iut.pm.teammanager.utils.MatchRecyclerViewAdapter
import kotlinx.android.synthetic.main.match_list_fragment.view.*

class MatchListFragment : Fragment() {

    companion object {
        private const val USER_ACCOUNT_ID = "user_account_id"
        private const val TEAM_ID = "team_id"

        fun newInstance(userAccountId: String, teamId: Long) = MatchListFragment().apply {
            arguments = bundleOf(USER_ACCOUNT_ID to userAccountId, TEAM_ID to teamId)
        }
    }

    private var matchList = ArrayList<MatchEntity>()
    private val matchListAdapter = MatchRecyclerViewAdapter(matchList)
    private var userAccountId: String = "0"
    private var teamId: Long = NEW_TEAM_ID

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val networkFragment = NetworkFragment()

        userAccountId = savedInstanceState?.getString(USER_ACCOUNT_ID) ?: arguments?.getString(USER_ACCOUNT_ID) ?: "0"
        teamId = savedInstanceState?.getLong(TEAM_ID) ?: arguments?.getLong(TEAM_ID) ?: NEW_TEAM_ID

        val user = getUserFromIdRes(teamId, userAccountId)

        Log.d("test", "onCreateView: $user")
        networkFragment.getUserHistory(user?.accountId)
        val view = inflater.inflate(R.layout.match_list_fragment, container, false)
        view.recycler_view_match.adapter = matchListAdapter
        return view
    }

    private fun getUserFromIdRes(teamId: Long, idRes: String) : User? {
        val teamRepo = TeamRepository(TeamDatabase.getInstance().teamDAO())
        val team = teamRepo.findById(teamId)

        if(idRes.contains("mid")) {
            return team.value?.midlaner
        }
        if(idRes.contains("adc")) {
            return team.value?.botlaner
        }
        if(idRes.contains("bot")) {
            return team.value?.support
        }
        if(idRes.contains("jgl")) {
            return team.value?.jungler
        }
        if(idRes.contains("top")) {
            return team.value?.toplaner
        }
        return null
    }
}
