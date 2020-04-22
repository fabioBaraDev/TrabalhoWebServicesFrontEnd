package br.com.fiap.controledealunos.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import br.com.fiap.controledealunos.R
import br.com.fiap.controledealunos.view.main.MainActivity

class SplashActivity : AppCompatActivity() {
    private val SPLASH_DISPLAY_TIME = 3500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        carregar()
    }

    fun carregar() {
        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            this@SplashActivity.finish()
        }, SPLASH_DISPLAY_TIME)
    }
}
