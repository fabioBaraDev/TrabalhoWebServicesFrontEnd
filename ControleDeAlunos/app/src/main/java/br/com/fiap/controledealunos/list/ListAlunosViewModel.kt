package br.com.fiap.controledealunos.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.controledealunos.model.Aluno
import br.com.fiap.controledealunos.repository.AlunoRepository

class ListAlunosViewModel(val alunoRepository: AlunoRepository) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val alunos = MutableLiveData<List<Aluno>>()
    val messageError = MutableLiveData<String>()

    fun getAlunos() {
        isLoading.value = true
        alunoRepository.getAluno(sort = "number,asc",
            size = 150,
            onComplete = {
                isLoading.value = false
                alunos.value = it
                messageError.value = ""
            },
            onError = {
                isLoading.value = false
                alunos.value = listOf()
                messageError.value = it?.message
            })
    }
}