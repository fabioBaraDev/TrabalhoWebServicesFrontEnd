package br.com.fiap.controledealunos.repository

import br.com.fiap.controledealunos.api.service.StatusAlunoService
import br.com.fiap.controledealunos.model.CreditoAlunoBody
import br.com.fiap.controledealunos.model.Status
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StatusRepositoryImpl(val statusAlunoService: StatusAlunoService) : StatusRepository {

    override fun getStatusAluno(
        id: String,
        onComplete: (Status?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        statusAlunoService.getStatusAluno(id).enqueue(object : Callback<Status> {
            override fun onFailure(call: Call<Status>, t: Throwable) {
                onError(t)
            }

            override fun onResponse(call: Call<Status>, response: Response<Status>) {
                if (response.isSuccessful) {
                    onComplete(response.body())
                } else {
                    onError(Throwable("Não foi possível realizar a requisição"))
                }
            }
        })

    }

    override fun setStatusHabilitar(
        aluno: CreditoAlunoBody,
        onComplete: (String?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        statusAlunoService.habilitar(aluno).enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                onError(t)
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    onComplete("Aluno habilitado com Sucesso")
                } else {
                    onError(Throwable("Não foi possível realizar a requisição"))
                }
            }
        })
    }

    override fun setStatusDesabilitar(
        aluno: CreditoAlunoBody,
        onComplete: (String?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        statusAlunoService.desabilitar(aluno).enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                onError(t)
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    onComplete("Aluno Desabilitado com Sucesso")
                } else {
                    onError(Throwable("Não foi possível realizar a requisição"))
                }
            }
        })
    }

    override fun deleteAluno(
        id: String,
        onComplete: (String?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        statusAlunoService.deleteAluno(id).enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                onError(t)
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    onComplete("Aluno Deletado com sucesso")
                } else {
                    onError(Throwable("Não foi possível realizar a requisição"))
                }
            }
        })
    }
}