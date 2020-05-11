package br.com.fiap.controledealunos.api.service

import br.com.fiap.controledealunos.model.Aluno
import br.com.fiap.controledealunos.model.Status
import br.com.fiap.controledealunos.model.StatusAlunoBody
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

    @POST("/cadastro/alunos/ativar")
    fun habilitar(
        @Body aluno : StatusAlunoBody
    ):Call<Void>

    @POST("/cadastro/alunos/desativar")
    fun desabilitar(
        @Body aluno : StatusAlunoBody
    ):Call<Void>
}