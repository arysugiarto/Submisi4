package com.arysugiarto.submisi.footballmatchschedule.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League (
    val name : String? , val image : Int? , val idLiga : String?) : Parcelable

