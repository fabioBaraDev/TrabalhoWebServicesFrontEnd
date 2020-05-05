package br.com.fiap.controledealunos.repository

import br.com.fiap.controledealunos.model.Aluno

interface AlunoRepository {
    fun getAluno(
        aluno: String,
        sort: String,
        size: Int,
        onComplete:(List<Aluno>?) -> Unit,
        onError:(Throwable?) -> Unit
    )
}