package br.com.fiap.controledealunos.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.controledealunos.model.Token
import br.com.fiap.controledealunos.model.User
import br.com.fiap.controledealunos.repository.AuthRepository

class AuthViewModel(val auth: AuthRepository) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val tokenUser = MutableLiveData<Token>()
    val messageError = MutableLiveData<String>()

    fun logon(user: User) {
        isLoading.value = true
        auth.getToken(user,
            onComplete = {
                isLoading.value = false
                tokenUser.value = it
                messageError.value = ""
            },
            onError = {
                isLoading.value = false
                tokenUser.value = null
                messageError.value = it?.message
            })
    }
}