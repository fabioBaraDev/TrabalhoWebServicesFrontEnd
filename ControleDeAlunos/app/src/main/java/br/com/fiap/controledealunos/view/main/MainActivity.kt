package br.com.fiap.controledealunos.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import br.com.fiap.controledealunos.R
import br.com.fiap.controledealunos.view.alunos.AdicionarAlunoActivity
import br.com.fiap.controledealunos.view.alunos.AlunosListaActivity
import br.com.fiap.controledealunos.view.logon.LogonActivity
import br.com.fiap.controledealunos.view.usuario.AdicionarUsuarioActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listeners()
    }

    private fun listeners(){
        val btAlunos = findViewById<Button>(R.id.bt_alunos_pesquisar)
        val etNome = findViewById<EditText>(R.id.et_nome_pesquisar)

        btAlunos.setOnClickListener {
            val intent = Intent(this@MainActivity, AlunosListaActivity::class.java)
            intent.putExtra("nome", etNome.text.toString().trim())
            intent.putExtra("nomePesquisado", etNome.text.toString().trim())
            startActivity(intent)
        }

        val btAdicionarAluno = findViewById<Button>(R.id.bt_adicionar_aluno)
        val btAdicionarADM = findViewById<Button>(R.id.bt_adicionar_adm)

        btAdicionarAluno.setOnClickListener{
            val intent = Intent(this@MainActivity, AdicionarAlunoActivity::class.java)
            startActivity(intent)
        }

        btAdicionarADM.setOnClickListener{
            val intent = Intent(this@MainActivity, AdicionarUsuarioActivity::class.java)
            startActivity(intent)
        }

        val btLogout = findViewById<Button>(R.id.bt_logout_logon)
        btLogout.setOnClickListener {
            val intent = Intent(this@MainActivity, LogonActivity::class.java)
            startActivity(intent)
            this@MainActivity.finish()
        }
    }
}
