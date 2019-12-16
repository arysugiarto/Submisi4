package com.arysugiarto.submisi.footballmatchschedule.entity

import com.google.gson.annotations.SerializedName
import kotlin.collections.List

data class TeamResponseDetail(
    @SerializedName("teams")
    var teams: List<Team>)