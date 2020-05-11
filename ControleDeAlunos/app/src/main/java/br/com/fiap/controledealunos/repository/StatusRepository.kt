package br.com.fiap.controledealunos.repository

import br.com.fiap.controledealunos.model.Status
import br.com.fiap.controledealunos.model.StatusAlunoBody

interface StatusRepository {
    fun getStatusAluno(
        id: String,
        onComplete: (Status?) -> Unit,
        onError: (Throwable?) -> Unit
    )

    fun setStatusHabilitar(
        aluno: StatusAlunoBody,
        onComplete: (String?) -> Unit,
        onError: (Throwable?) -> Unit
    )

    fun setStatusDesabilitar(
        aluno: StatusAlunoBody,
        onComplete: (String?) -> Unit,
        onError: (Throwable?) -> Unit
    )

    fun deleteAluno(
        id: String,
        onComplete: (String?) -> Unit,
        onError: (Throwable?) -> Unit
    )
}