package br.com.fiap.controledealunos.repository

import br.com.fiap.controledealunos.model.Aluno
import br.com.fiap.controledealunos.model.CreditoAlunoBody
import br.com.fiap.controledealunos.model.Status
import okhttp3.RequestBody

interface StatusRepository {
    fun getStatusAluno(
        id: String,
        onComplete: (Status?) -> Unit,
        onError: (Throwable?) -> Unit
    )

    fun setStatusHabilitar(
        aluno: CreditoAlunoBody,
        onComplete: (String?) -> Unit,
        onError: (Throwable?) -> Unit
    )

    fun setStatusDesabilitar(
        aluno: CreditoAlunoBody,
        onComplete: (String?) -> Unit,
        onError: (Throwable?) -> Unit
    )

    fun deleteAluno(
        id: String,
        onComplete: (String?) -> Unit,
        onError: (Throwable?) -> Unit
    )
}