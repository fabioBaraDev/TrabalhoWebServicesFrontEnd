package br.com.fiap.controledealunos.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.controledealunos.model.CreditoAlunoBody
import br.com.fiap.controledealunos.model.Status
import br.com.fiap.controledealunos.repository.StatusRepository

class StatusAlunoViewModel(val statusAlunoRepository: StatusRepository) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val statusAluno = MutableLiveData<Status>()
    val messageError = MutableLiveData<String>()
    val statusHabilitar = MutableLiveData<String>()
    val statusDelecao = MutableLiveData<String>()

    fun deletarAluno(id: String) {
        isLoading.value = true
        statusAlunoRepository.deleteAluno(id,
            onComplete = {
                isLoading.value = false
                statusDelecao.value = it
                messageError.value = ""
            },
            onError = {
                isLoading.value = false
                statusDelecao.value = null
                messageError.value = it?.message
            })
    }

    fun setDesabilitar(aluno: CreditoAlunoBody) {
        isLoading.value = true
        statusAlunoRepository.setStatusDesabilitar(aluno,
            onComplete = {
                isLoading.value = false
                statusHabilitar.value = it
                messageError.value = ""
            },
            onError = {
                isLoading.value = false
                statusHabilitar.value = null
                messageError.value = it?.message
            })
    }

    fun setHabilitar(aluno: CreditoAlunoBody) {
        isLoading.value = true
        statusAlunoRepository.setStatusHabilitar(aluno,
            onComplete = {
                isLoading.value = false
                statusHabilitar.value = it
                messageError.value = ""
            },
            onError = {
                isLoading.value = false
                statusHabilitar.value = null
                messageError.value = it?.message
            })
    }


    fun getStatusAluno(id: String) {
        isLoading.value = true
        statusAlunoRepository.getStatusAluno(id = id,
            onComplete = {
                isLoading.value = false
                statusAluno.value = it
                messageError.value = ""
            },
            onError = {
                isLoading.value = false
                statusAluno.value = null
                messageError.value = it?.message
            })
    }
}