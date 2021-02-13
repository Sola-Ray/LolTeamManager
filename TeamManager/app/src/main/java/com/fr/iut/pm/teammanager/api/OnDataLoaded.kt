package com.fr.iut.pm.teammanager.api

import com.fr.iut.pm.teammanager.model.User

interface OnDataLoaded {

    fun onSuccess(value: User?)
    fun onFailure()
}