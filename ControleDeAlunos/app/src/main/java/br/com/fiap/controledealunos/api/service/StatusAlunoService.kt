package br.com.fiap.controledealunos.api.service

import br.com.fiap.controledealunos.model.Aluno
import br.com.fiap.controledealunos.model.CreditoAlunoBody
import br.com.fiap.controledealunos.model.Status
import retrofit2.Call
import retrofit2.http.*


interface StatusAlunoService{

    @GET("/cadastro/alunos/status/{id}")
    fun getStatusAluno(
        @Path("id") id: String
    ) : Call<Status>

    @DELETE("/cadastro/alunos/{id}")
    fun deleteAluno(
        @Path("id") id: String
    ):Call<Void>

    @POST("/transacoes/credito/ativar/")
    fun habilitar(
        @Body aluno : CreditoAlunoBody
    ):Call<Void>

    @POST("/transacoes/credito/desativar/")
    fun desabilitar(
        @Body aluno : CreditoAlunoBody
    ):Call<Void>
}