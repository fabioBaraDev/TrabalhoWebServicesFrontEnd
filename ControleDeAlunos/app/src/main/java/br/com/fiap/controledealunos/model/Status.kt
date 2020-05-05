package br.com.fiap.controledealunos.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Status(
    @SerializedName("status_aluno") val status : Boolean
): Parcelable