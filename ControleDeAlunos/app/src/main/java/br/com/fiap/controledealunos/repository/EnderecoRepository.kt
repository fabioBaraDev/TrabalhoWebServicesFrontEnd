package br.com.fiap.controledealunos.repository

import br.com.fiap.controledealunos.model.Endereco

interface EnderecoRepository {
    fun getEnderecoAluno(
        id: String,
        onComplete: (Endereco?) -> Unit,
        onError: (Throwable?) -> Unit
    )
}