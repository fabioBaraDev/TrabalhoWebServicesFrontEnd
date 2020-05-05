package br.com.fiap.controledealunos.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import br.com.fiap.controledealunos.R
import br.com.fiap.controledealunos.view.alunos.AlunosListaActivity

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
    }
}
