package br.com.fiap.controledealunos.repository

import br.com.fiap.controledealunos.api.service.EnderecoService
import br.com.fiap.controledealunos.model.Endereco
import br.com.fiap.controledealunos.model.Status
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EnderecoRepositoryImpl(val enderecoService: EnderecoService) : EnderecoRepository {
    override fun getEnderecoAluno(
        id: String,
        onComplete: (Endereco?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        enderecoService.getAlunos(id).enqueue(object : Callback<Endereco> {
            override fun onFailure(call: Call<Endereco>, t: Throwable) {
                onError(t)
            }

            override fun onResponse(call: Call<Endereco>, response: Response<Endereco>) {
                if (response.isSuccessful) {
                    onComplete(response.body())
                } else {
                    onError(Throwable("Não foi possível realizar a requisição"))
                }
            }
        })
    }
}