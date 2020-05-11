package br.com.fiap.controledealunos.view.alunos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import br.com.fiap.controledealunos.R
import br.com.fiap.controledealunos.view.logon.LogonActivity

class AdicionarAlunoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_aluno)

        onClickLogout()
    }

    private fun onClickLogout(){
        val btLogout = findViewById<Button>(R.id.bt_logout_add_aluno)
        btLogout.setOnClickListener {
            val intent = Intent(this@AdicionarAlunoActivity, LogonActivity::class.java)
            startActivity(intent)
            this@AdicionarAlunoActivity.finish()
        }
    }

}
