package br.com.fiap.controledealunos.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Aluno(
    @SerializedName("nome") val nome : String,
    @SerializedName("numeroCartao") val numeroCartao : String,
    @SerializedName("id") val id : String
): Parcelable