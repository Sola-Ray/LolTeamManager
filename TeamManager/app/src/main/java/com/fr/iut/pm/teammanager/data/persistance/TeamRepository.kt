package com.fr.iut.pm.teammanager.data.persistance

import com.fr.iut.pm.teammanager.data.persistance.dao.TeamDAO
import com.fr.iut.pm.teammanager.model.Team
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *  La source de vérité de l'information. Ici c'est juste un wrapper
 *  à la DAO qui permet d'exécuter en asynchrone les opérations qui
 *  le nécessitent.
 *  Dans la vraie vie on y fait aussi le choix de piocher l'information
 *  au bon endroit (BdD, fichier, source en remote, …)
 */
class TeamRepository(private val teamDAO: TeamDAO) {
    suspend fun insert(team: Team) = withContext(Dispatchers.IO) { teamDAO.insert(team) }
    suspend fun delete(team: Team) = withContext(Dispatchers.IO) { teamDAO.delete(team) }
    suspend fun update(team: Team) = withContext(Dispatchers.IO) { teamDAO.update(team) }

    fun findById(teamId: Long) = teamDAO.findById(teamId)
    fun getAll() = teamDAO.getAll()
}