package com.dor.compose.playground.composedestinationslibrary.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(val name: String, val surname: String) : Parcelable