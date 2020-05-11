package br.com.fiap.controledealunos.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.controledealunos.model.Aluno
import br.com.fiap.controledealunos.repository.AlunoRepository

class AlunoViewModel(val alunoRepository: AlunoRepository) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val alunos = MutableLiveData<String>()
    val messageError = MutableLiveData<String>()

    fun criaAluno(aluno: Aluno) {
        isLoading.value = true
        alunoRepository.criarAluno(aluno,
            onComplete = {
                isLoading.value = false
                alunos.value = it
                messageError.value = ""
            },
            onError = {
                isLoading.value = false
                alunos.value = "Erro"
                messageError.value = it?.message
            })
    }
}