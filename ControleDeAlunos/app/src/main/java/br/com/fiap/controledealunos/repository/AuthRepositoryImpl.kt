package br.com.fiap.controledealunos.repository

import br.com.fiap.controledealunos.api.service.AuthService
import br.com.fiap.controledealunos.model.Token
import br.com.fiap.controledealunos.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepositoryImpl (val auth : AuthService) : AuthRepository {
    override fun getToken(user: User, onComplete: (Token?) -> Unit, onError: (Throwable?) -> Unit) {
        auth.login(user).enqueue(object : Callback<Token> {

            override fun onFailure(call: Call<Token>, t: Throwable) {
                onError(t)
            }

            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                if (response.isSuccessful) {
                    onComplete(response.body())
                } else {
                    onError(Throwable("Não foi possível realizar a requisição"))
                }
            }
        })
    }
}