package br.com.fiap.controledealunos

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import br.com.fiap.controledealunos.di.networkModule
import br.com.fiap.controledealunos.di.repositoryModule
import br.com.fiap.controledealunos.di.viewModelModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.util.*

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        //cria sessao
        var session =
        getSharedPreferences("br.com.fiap.controle-alunos", Context.MODE_PRIVATE)
        session.edit().putString("token", "").commit()

        // Start stetho
        Stetho.initializeWithDefaults(this)
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    viewModelModule,
                    networkModule,
                    repositoryModule
                )
            )
        }
    }
}