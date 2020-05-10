package br.com.fiap.controledealunos.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.controledealunos.model.User
import br.com.fiap.controledealunos.repository.UsuariosRepository

class UsuariosViewModel(val repos: UsuariosRepository) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val statusUsuarioADM = MutableLiveData<String>()
    val messageError = MutableLiveData<String>()

    fun adicionarADM(usu: User) {
        isLoading.value = true
        repos.getStatusAluno(usu,
            onComplete = {
                isLoading.value = false
                statusUsuarioADM.value = it
                messageError.value = ""
            },
            onError = {
                isLoading.value = false
                statusUsuarioADM.value = null
                messageError.value = it?.message
            })
    }
}