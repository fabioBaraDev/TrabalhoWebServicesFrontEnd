package br.com.fiap.controledealunos.api.service

import br.com.fiap.controledealunos.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UsuariosService{

    @POST("/auth/user/")
    fun setUsuarioADM(
        @Body usuario: User
    ) : Call<Void>
}