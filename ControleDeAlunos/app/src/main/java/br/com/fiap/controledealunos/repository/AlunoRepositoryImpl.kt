package br.com.fiap.controledealunos.repository

import br.com.fiap.controledealunos.model.Aluno
import br.com.fiap.controledealunos.model.AlunoResponse
import br.com.fiap.controledealunos.api.service.ListaAlunosService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlunoRepositoryImpl(val alunoService: ListaAlunosService) : AlunoRepository {

    override fun getAluno(
        sort: String,
        size: Int,
        onComplete: (List<Aluno>?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        alunoService
            .getAlunos(sort, size)
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
}