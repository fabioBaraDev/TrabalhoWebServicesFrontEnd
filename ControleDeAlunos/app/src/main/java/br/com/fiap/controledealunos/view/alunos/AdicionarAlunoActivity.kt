package br.com.fiap.controledealunos.view.alunos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import br.com.fiap.controledealunos.R
import br.com.fiap.controledealunos.model.Aluno
import br.com.fiap.controledealunos.model.Endereco
import br.com.fiap.controledealunos.view.logon.LogonActivity
import br.com.fiap.controledealunos.view.main.MainActivity
import br.com.fiap.controledealunos.viewModel.AlunoViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class AdicionarAlunoActivity : AppCompatActivity() {

    val alunoViewModel: AlunoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_aluno)

        onClickLogout()
        onClickAdicionar()
    }

    private fun onClickAdicionar() {
        val btAdd = findViewById<Button>(R.id.bt_adiciona_novo_aluno)
        btAdd.setOnClickListener {

            val endereco =
                Endereco(
                    findViewById<EditText>(R.id.et_add_aluno_logradouro).text.toString(),
                    findViewById<EditText>(R.id.et_add_aluno_numero).text.toString(),
                    findViewById<EditText>(R.id.et_add_aluno_complemento).text.toString(),
                    findViewById<EditText>(R.id.et_add_aluno_cep).text.toString()
                )
            val aluno =
            Aluno(
                findViewById<EditText>(R.id.et_add_aluno_nome).text.toString(),
                findViewById<EditText>(R.id.et_add_aluno_numero_cartao).text.toString(),
                "",
                 endereco
            )

            alunoViewModel.criaAluno(aluno)
            alunoViewModel.alunos.observe(this, Observer {
                Toast.makeText(this, "Adicionado com sucesso", Toast.LENGTH_LONG).show()
                val intent = Intent(this@AdicionarAlunoActivity, MainActivity::class.java)
                startActivity(intent)
                this@AdicionarAlunoActivity.finish()
            })

            alunoViewModel.messageError.observe(this, Observer {
                if (it != "") {
                    Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    private fun onClickLogout() {
        val btLogout = findViewById<Button>(R.id.bt_logout_add_aluno)
        btLogout.setOnClickListener {
            val intent = Intent(this@AdicionarAlunoActivity, LogonActivity::class.java)
            startActivity(intent)
            this@AdicionarAlunoActivity.finish()
        }
    }
}
