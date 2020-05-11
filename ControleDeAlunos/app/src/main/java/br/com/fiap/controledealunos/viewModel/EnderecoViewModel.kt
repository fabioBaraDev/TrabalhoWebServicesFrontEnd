package br.com.fiap.controledealunos.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.controledealunos.model.Endereco
import br.com.fiap.controledealunos.model.User
import br.com.fiap.controledealunos.repository.EnderecoRepository

class EnderecoViewModel(val repository: EnderecoRepository) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val statusEndereco = MutableLiveData<Endereco>()
    val messageError = MutableLiveData<String>()

    fun getEndereco(id: String) {
        isLoading.value = true
        repository.getEnderecoAluno(id,
            onComplete = {
                isLoading.value = false
                statusEndereco.value = it
                messageError.value = ""
            },
            onError = {
                isLoading.value = false
                statusEndereco.value = null
                messageError.value = it?.message
            })
    }
}