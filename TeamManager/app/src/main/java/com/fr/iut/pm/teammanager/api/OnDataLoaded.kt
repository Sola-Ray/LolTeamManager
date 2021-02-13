package com.fr.iut.pm.teammanager.api

import com.fr.iut.pm.teammanager.model.User

interface OnDataLoaded {

    fun onSucess(value: User?)
    fun onFailure()
}