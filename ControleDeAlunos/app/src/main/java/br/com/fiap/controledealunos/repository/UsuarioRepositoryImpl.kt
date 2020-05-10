package br.com.fiap.controledealunos.repository

import br.com.fiap.controledealunos.api.service.UsuariosService
import br.com.fiap.controledealunos.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsuarioRepositoryImpl (val service : UsuariosService) : UsuariosRepository{
    override fun getStatusAluno(
        usuario: User,
        onComplete: (String?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        service.setUsuarioADM(usuario).enqueue(object : Callback<Void>{
            override fun onFailure(call: Call<Void>, t: Throwable) {
                onError(t)
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    onComplete("Adicionado com sucesso")
                } else {
                    onError(Throwable("Não foi possível realizar a requisição"))
                }
            }
        })
    }
}