package br.com.fiap.controledealunos.di

import br.com.fiap.controledealunos.api.interceptor.AuthInterceptor
import br.com.fiap.controledealunos.api.service.AuthService
import br.com.fiap.controledealunos.viewModel.ListAlunosViewModel
import br.com.fiap.controledealunos.api.service.ListaAlunosService
import br.com.fiap.controledealunos.api.service.StatusAlunoService
import br.com.fiap.controledealunos.repository.*
import br.com.fiap.controledealunos.viewModel.AuthViewModel
import br.com.fiap.controledealunos.viewModel.StatusAlunoViewModel
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://escola-contas.herokuapp.com"

private fun createNetworkClient(okHttpClient: OkHttpClient): Retrofit {

    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
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
    viewModel { StatusAlunoViewModel(get()) }
    viewModel { AuthViewModel(get()) }
}
val repositoryModule = module {
    single<AlunoRepository> { AlunoRepositoryImpl(get()) }
    single<StatusRepository> { StatusRepositoryImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}
val networkModule = module {
    single<Interceptor> { AuthInterceptor() }
    single { createNetworkClient(get()).create(ListaAlunosService::class.java) }
    single { createNetworkClient(get()).create(StatusAlunoService::class.java) }
    single { createNetworkClient(get()).create(AuthService::class.java) }
    single { createOkhttpClientAuth(get()) }
}
