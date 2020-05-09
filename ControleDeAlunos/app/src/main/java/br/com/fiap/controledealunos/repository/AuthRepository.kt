package br.com.fiap.controledealunos.repository

import br.com.fiap.controledealunos.model.Token
import br.com.fiap.controledealunos.model.User

interface AuthRepository{
    fun getToken(
        user: User,
        onComplete: (Token?) -> Unit,
        onError: (Throwable?) -> Unit
    )
}