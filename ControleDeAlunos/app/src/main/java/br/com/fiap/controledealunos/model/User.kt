package br.com.fiap.controledealunos.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class User(
    var username: String,
    val password: String
)