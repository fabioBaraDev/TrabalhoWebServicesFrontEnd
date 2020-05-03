package br.com.fiap.controledealunos.api.service

import br.com.fiap.controledealunos.model.Aluno
import br.com.fiap.controledealunos.model.AlunoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ListaAlunosService {
  //  @GET("/cadastro/aluno/todos")
  //  fun getAlunos(): Call <AlunoResponse>

    @GET("/cadastro/aluno/nome/fabio")
    fun getAlunos(
        @Query("sort") sort: String,
        @Query("size") size: Int
    ) : Call<List<Aluno>>
}