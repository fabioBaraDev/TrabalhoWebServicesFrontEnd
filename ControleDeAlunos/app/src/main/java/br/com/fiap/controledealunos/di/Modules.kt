package br.com.fiap.controledealunos.di

import br.com.fiap.controledealunos.api.interceptor.AuthInterceptor
import br.com.fiap.controledealunos.list.ListAlunosViewModel
import br.com.fiap.controledealunos.repository.AlunoRepository
import br.com.fiap.controledealunos.repository.AlunoRepositoryImpl
import br.com.fiap.controledealunos.api.service.ListaAlunosService
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private fun createNetworkClient(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://escola-contas.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun createOkhttpClientAuth(authInterceptor: Interceptor): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addNetworkInterceptor(StethoInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
    return builder.build()
}

val viewModelModule = module {
    viewModel { ListAlunosViewModel(get()) }
}
val repositoryModule = module {
    single<AlunoRepository> { AlunoRepositoryImpl(get()) }
}
val networkModule = module {
    single<Interceptor> { AuthInterceptor() }
    single { createNetworkClient(get()).create(ListaAlunosService::class.java) }
    single { createOkhttpClientAuth(get()) }
}