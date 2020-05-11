package br.com.fiap.controledealunos.api.service

import br.com.fiap.controledealunos.model.Aluno
import retrofit2.Call
import retrofit2.http.*

interface AlunoService {

    @GET("/cadastro/alunos/nome/{nome}")
    fun getAlunos(
        @Path("nome") id: String,
        @Query("sort") sort: String,
        @Query("size") size: Int
    ): Call<List<Aluno>>

    @POST("/cadastro/alunos/")
    fun criarAluno(
        @Body aluno: Aluno
    ): Call<Void>
}