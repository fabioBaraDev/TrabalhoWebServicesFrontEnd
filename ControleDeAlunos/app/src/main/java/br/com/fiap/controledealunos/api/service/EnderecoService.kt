package br.com.fiap.controledealunos.api.service

import br.com.fiap.controledealunos.model.Endereco
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoService {

    @GET("/cadastro/endereco/{id}")
    fun getAlunos(
        @Path("id") id: String
    ) : Call<Endereco>
}