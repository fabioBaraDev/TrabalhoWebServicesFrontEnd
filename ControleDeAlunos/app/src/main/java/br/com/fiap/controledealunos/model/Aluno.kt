package br.com.fiap.controledealunos.model

import com.google.gson.annotations.SerializedName

data class Aluno(
    @SerializedName("nome") val nome : String,
    @SerializedName("numeroCartao") val numeroCartao : String,
    @SerializedName("id") val id : String,
    @SerializedName("endereco") val endereco : Endereco
)