package br.com.fiap.controledealunos.repository

import br.com.fiap.controledealunos.model.User

interface UsuariosRepository{
    fun getStatusAluno(
        usuario: User,
        onComplete: (String?) -> Unit,
        onError: (Throwable?) -> Unit
    )
}