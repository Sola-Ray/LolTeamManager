package com.fr.iut.pm.teammanager.utils

import android.view.View
import androidx.databinding.InverseMethod
import com.fr.iut.pm.teammanager.converter.UserToStringConverter
import com.fr.iut.pm.teammanager.model.User

object Converters {

    /**
     * Rend visible ou non une vue en fonction de si elle est vide
     */
    @JvmStatic
    fun listEmptyToVisibility(empty: Boolean): Int {
        return if (empty) View.VISIBLE else View.GONE
    }
}
