package br.com.fiap.controledealunos.view.alunos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.fiap.controledealunos.R
import br.com.fiap.controledealunos.model.AlunoBody
import br.com.fiap.controledealunos.model.CreditoAlunoBody
import br.com.fiap.controledealunos.view.logon.LogonActivity
import br.com.fiap.controledealunos.viewModel.StatusAlunoViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetalhesActivity : AppCompatActivity() {

    val statusAlunoViewModel: StatusAlunoViewModel by viewModel()

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
        onCLickHabilitar(valueID, valueNomePesquisado)
        onClickDeletar(valueID, valueNomePesquisado)
        onClickLogout()
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
                statusAlunoViewModel.setHabilitar(criaBodyReq(id))
            }else{
                statusAlunoViewModel.setDesabilitar(criaBodyReq(id))
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

    private fun criaBodyReq(id : String): CreditoAlunoBody {
        return CreditoAlunoBody(AlunoBody("fulano", "123", id), "0.0")
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
