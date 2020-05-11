package br.com.fiap.controledealunos.api.service

import br.com.fiap.controledealunos.model.Aluno
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Aluno{

    @POST("/transacoes/credito/ativar/")
    fun habilitar(
        @Body aluno : Aluno
    ): Call<Void>
}