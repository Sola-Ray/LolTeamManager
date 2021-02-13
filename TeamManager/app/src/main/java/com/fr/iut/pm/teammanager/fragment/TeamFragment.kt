package com.fr.iut.pm.teammanager.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.text.format.DateFormat
import android.util.Log
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.fr.iut.pm.teammanager.R
import com.fr.iut.pm.teammanager.api.OnDataLoaded
import com.fr.iut.pm.teammanager.databinding.TeamFragmentBinding
import com.fr.iut.pm.teammanager.model.NEW_TEAM_ID
import com.fr.iut.pm.teammanager.model.User
import com.fr.iut.pm.teammanager.utils.viewModelFactory
import com.fr.iut.pm.teammanager.viewmodel.TeamViewModel
import kotlinx.android.synthetic.main.item_team.*
import kotlinx.android.synthetic.main.item_team.view.*
import kotlinx.android.synthetic.main.team_fragment.*
import kotlinx.android.synthetic.main.team_fragment.view.*

class TeamFragment : Fragment(), OnDataLoaded {

    companion object {
        private const val MY_TEAM_ID = "my_team_id"

        fun newInstance(teamId: Long) = TeamFragment().apply {
            arguments = bundleOf(MY_TEAM_ID to teamId)
        }
    }

    private lateinit var teamVM: TeamViewModel
    private var teamId: Long = NEW_TEAM_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        teamId = savedInstanceState?.getLong(MY_TEAM_ID) ?: arguments?.getLong(MY_TEAM_ID) ?: NEW_TEAM_ID

        if(teamId == NEW_TEAM_ID) {
            requireActivity().title = getString(R.string.add_team)
        }

        setHasOptionsMenu(true)

        teamVM = ViewModelProvider(this, viewModelFactory { TeamViewModel(teamId) }).get()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(MY_TEAM_ID, teamId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val teamBinding = TeamFragmentBinding.inflate(inflater)
        teamBinding.teamVM = teamVM
        teamBinding.lifecycleOwner = viewLifecycleOwner

        return teamBinding.root
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
            throw RuntimeException("$context doit implémenter OnInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        if (teamId == NEW_TEAM_ID) {
            menu.findItem(R.id.action_delete)?.isVisible = false
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_team, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                saveTeam()
                true
            }
            R.id.action_delete -> {
                deleteTeam()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveTeam() {
        if (teamVM.saveTeam() == false) {
            /*AlertDialog.Builder(requireContext())
                .setTitle(R.string.create_dog_error_dialog_title)
                .setMessage(R.string.create_dog_error_message)
                .setNeutralButton(android.R.string.ok, null)
                .show()*/
            return
        }
        /*val networkFragment = NetworkFragment()

        networkFragment.setUserFromStringAndApi(edit_support.text.toString(), this)
        team.botlaner = networkFragment.getUserApiFromString(edit_adc.toString(), img_user_adc, this)
        team.midlaner = networkFragment.getUserApiFromString(edit_midlaner.toString(), img_user_mid)
        team.toplaner = networkFragment.getUserApiFromString(edit_toplaner.toString(), img_user_top)
        team.jungler = networkFragment.getUserApiFromString(edit_jgler.toString(), img_user_jgl)*/

        listener?.onTeamSaved()
    }


    private fun deleteTeam() {
        teamVM.deleteTeam()
        listener?.onTeamDeleted()
    }

    override fun onSucess(value: User?) {
        Log.d("test", "onSucess: $value")
    }

    override fun onFailure() {
        TODO("Not yet implemented")
    }
}