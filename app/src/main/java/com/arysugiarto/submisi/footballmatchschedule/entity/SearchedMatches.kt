package com.arysugiarto.submisi.footballmatchschedule.entity

import com.google.gson.annotations.SerializedName

data class SearchedMatches (
    @SerializedName("event") var events: kotlin.collections.List<List>
    )