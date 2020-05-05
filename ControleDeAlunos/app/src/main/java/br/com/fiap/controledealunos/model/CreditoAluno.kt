package br.com.fiap.controledealunos.model

data class CreditoAlunoBody(
    val aluno : AlunoBody,
    val saldo : String
)

data class AlunoBody(
    val nome : String,
    val numeroCartao : String,
    val id : String
)