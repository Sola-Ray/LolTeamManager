package com.fr.iut.pm.teammanager.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fr.iut.pm.teammanager.data.persistance.TeamDatabase
import com.fr.iut.pm.teammanager.data.persistance.TeamRepository
import com.fr.iut.pm.teammanager.model.Team
import kotlinx.coroutines.launch

class TeamListViewModel : ViewModel() {
    private val teamRepo = TeamRepository(TeamDatabase.getInstance().teamDAO())

    val teamList = teamRepo.getAll()
}