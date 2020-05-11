package br.com.fiap.controledealunos.repository

import br.com.fiap.controledealunos.api.service.AlunoService
import br.com.fiap.controledealunos.model.Aluno
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlunoRepositoryImpl(val alunoService: AlunoService) : AlunoRepository {

    override fun getAluno(
        nome: String,
        sort: String,
        size: Int,
        onComplete: (List<Aluno>?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        alunoService
            .getAlunos(nome, sort, size)
            .enqueue(object : Callback<List<Aluno>> {

            override fun onFailure(call: Call<List<Aluno>>, t: Throwable) {
                onError(t)
            }

            override fun onResponse(call: Call<List<Aluno>>, response: Response<List<Aluno>>) {
                if (response.isSuccessful) {
                    onComplete(response.body()?: listOf())
                } else {
                    onError(Throwable("Não foi possível realizar a requisição"))
                }
            }
        })
    }

    override fun criarAluno(
        aluno: Aluno,
        onComplete: (String?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        alunoService.criarAluno(aluno).enqueue(object : Callback<Void>{
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