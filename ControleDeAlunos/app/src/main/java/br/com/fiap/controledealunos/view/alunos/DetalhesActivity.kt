package br.com.fiap.controledealunos.view.alunos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.fiap.controledealunos.R
import br.com.fiap.controledealunos.model.Endereco
import br.com.fiap.controledealunos.model.StatusAlunoBody
import br.com.fiap.controledealunos.view.logon.LogonActivity
import br.com.fiap.controledealunos.viewModel.EnderecoViewModel
import br.com.fiap.controledealunos.viewModel.StatusAlunoViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetalhesActivity : AppCompatActivity() {

    val statusAlunoViewModel: StatusAlunoViewModel by viewModel()
    val enderecoAlunoViewModel: EnderecoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        val extras = intent.extras
        var valueNomePesquisado = ""
        var valueNome = ""
        var valueID = ""
        if (extras != null) {
            valueNome = extras.getString("nome").toString()
            valueID = extras.getInt("position").toString()
            valueNomePesquisado = extras.getString("nomePesquisado").toString()
        }

        val nome = findViewById<TextView>(R.id.tx_detalhes_nome)
        nome.text = valueNome

        getStatusAluno(valueID)
        getEnderecoAluno(valueID)
        onCLickHabilitar(valueID, valueNomePesquisado)
        onClickDeletar(valueID, valueNomePesquisado)
        onClickLogout()
    }

    private fun getEnderecoAluno(id: String) {
        enderecoAlunoViewModel.getEndereco(id)
        enderecoAlunoViewModel.statusEndereco.observe(this, Observer {
            popuparEnderecoFields(it)
        })
        enderecoAlunoViewModel.messageError.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun popuparEnderecoFields(endereco: Endereco?) {
       findViewById<TextView>(R.id.tv_detalhe_logradouro).text = endereco?.logradouro
       findViewById<TextView>(R.id.tv_detalhe_numero).text = endereco?.numero
       findViewById<TextView>(R.id.tv_detalhe_complemento).text = endereco?.complemento
       findViewById<TextView>(R.id.tv_detalhe_cep).text = endereco?.cep
    }

    private fun onClickDeletar(id: String, nome: String){
        val btDeletar = findViewById<Button>(R.id.bt_deletar)
        btDeletar.setOnClickListener{
            statusAlunoViewModel.deletarAluno(id)
            statusAlunoViewModel.statusDelecao.observe( this, Observer {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                callIntent(nome)
            })

            statusAlunoViewModel.messageError.observe(this, Observer {
                if (it != "") {
                    Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    private fun onClickLogout(){
        val btLogout = findViewById<Button>(R.id.bt_logout_detalhes)
        btLogout.setOnClickListener {
            val intent = Intent(this@DetalhesActivity, LogonActivity::class.java)
            startActivity(intent)
            this@DetalhesActivity.finish()
        }
    }

    private fun onCLickHabilitar(id : String, nome: String) {
        val btHabilitar = findViewById<Button>(R.id.bt_habilitar)
        btHabilitar.setOnClickListener {

            if(btHabilitar.text.equals("Habilitar")){
                statusAlunoViewModel.setHabilitar(StatusAlunoBody(id, "true"))
            }else{
                statusAlunoViewModel.setDesabilitar(StatusAlunoBody(id, "false"))
            }

            statusAlunoViewModel.messageError.observe(this, Observer {
                if (it != "") {
                    Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                }
            })

            statusAlunoViewModel.statusHabilitar.observe(this, Observer {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                callIntent(nome)
            })
        }
    }

    private fun callIntent(nome: String){
        val intent = Intent(this@DetalhesActivity, AlunosListaActivity::class.java)
        intent.putExtra("nomePesquisado", nome)
        intent.putExtra("nome", nome)
        startActivity(intent)
        this@DetalhesActivity.finish()
    }

    private fun getStatusAluno(id: String) {
        statusAlunoViewModel.getStatusAluno(id)
        statusAlunoViewModel.messageError.observe(this, Observer {
            if (it != "") {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })

        statusAlunoViewModel.statusAluno.observe(this, Observer {
            val bt_habilitar = findViewById<TextView>(R.id.bt_habilitar)

            if (it.status.toString().toBoolean()) {
                bt_habilitar.text = "Desabilitar"
            } else {
                bt_habilitar.text = "Habilitar"
            }
        })
    }
}
