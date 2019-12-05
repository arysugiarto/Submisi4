package com.arysugiarto.submisi.footballmatchschedule.preft

import android.content.Context

class SharedPref(context: Context) {
    val PREF = "preft"
    val ID_LIGA = "id_league"

    val preference = context.getSharedPreferences(PREF,Context.MODE_PRIVATE)

    fun setIdLiga(idLiga : String){
        val editor = preference.edit()
        editor.putString(ID_LIGA,idLiga)
        editor.apply()
    }
}