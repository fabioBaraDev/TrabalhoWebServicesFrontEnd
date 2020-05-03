package br.com.fiap.controledealunos.model

import com.google.gson.annotations.SerializedName

data class AlunoResponse(
    @SerializedName("content")
    val alunos: List<Aluno>
)