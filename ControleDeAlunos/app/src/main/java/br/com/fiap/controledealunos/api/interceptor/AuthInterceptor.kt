package br.com.fiap.controledealunos.api.interceptor

import android.util.Log
import br.com.fiap.controledealunos.Session
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {

        val requestBuilder = chain!!.request().newBuilder()

        if(!Session.data.get("token").isNullOrEmpty()){
            requestBuilder.addHeader("Authorization", "Bearer " + Session.data.get("token"))
        }

        val request = requestBuilder.build()
        val response = chain.proceed(request)
        if (response.code() == 401) {
            Log.e("MEUAPP", "Error API KEY")
        }
        return response
    }
}
