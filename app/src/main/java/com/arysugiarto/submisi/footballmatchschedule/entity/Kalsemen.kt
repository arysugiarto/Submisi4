package com.arysugiarto.submisi.footballmatchschedule.entity

import com.google.gson.annotations.SerializedName
import kotlin.collections.List

data class Kalsemen (
        @SerializedName("table") var table: List<TableX>
    )