package br.com.fiap.controledealunos.view.alunos

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.controledealunos.R

class DetalhesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        val extras = intent.extras
        var value = ""
        if (extras != null) {
            value = extras.getString("nome").toString()
        }
        val nome = findViewById<TextView>(R.id.tx_detalhes_nome)
        nome.text = value
    }
}
