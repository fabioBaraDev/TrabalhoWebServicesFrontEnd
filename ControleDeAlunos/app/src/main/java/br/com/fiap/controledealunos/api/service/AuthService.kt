package br.com.fiap.controledealunos.api.service

import br.com.fiap.controledealunos.model.Token
import br.com.fiap.controledealunos.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService{
    @POST("/auth/token")
    fun login(
        @Body aluno : User
    ):Call<Token>
}

