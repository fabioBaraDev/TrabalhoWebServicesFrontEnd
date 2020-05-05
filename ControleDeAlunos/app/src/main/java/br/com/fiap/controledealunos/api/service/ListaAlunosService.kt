package br.com.fiap.controledealunos.api.service

import br.com.fiap.controledealunos.model.Aluno
import br.com.fiap.controledealunos.model.AlunoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ListaAlunosService {

    @GET("/cadastro/aluno/nome/{nome}")
    fun getAlunos(
        @Path("nome") id: String,
        @Query("sort") sort: String,
        @Query("size") size: Int
    ) : Call<List<Aluno>>
}