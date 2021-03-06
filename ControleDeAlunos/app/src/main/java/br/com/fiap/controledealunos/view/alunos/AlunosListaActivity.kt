package br.com.fiap.controledealunos.view.alunos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.controledealunos.R
import br.com.fiap.controledealunos.viewModel.ListAlunosViewModel
import br.com.fiap.controledealunos.recyclerviews.AlunosViewHolder
import br.com.fiap.controledealunos.view.logon.LogonActivity
import br.com.fiap.controledealunos.view.main.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel

class AlunosListaActivity : AppCompatActivity(), AlunosViewHolder.OnNoteListener {

    val listAlunosViewModel: ListAlunosViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alunos_lista)

        onClickNovaPesquisa()
        onClickLogout()

        listAlunosViewModel.getAlunos(getName("nome"))

        listAlunosViewModel.messageError.observe(this, Observer {
            if (it != "") {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })

        listAlunosViewModel.alunos.observe(this, Observer {
            val listRecyclerView = findViewById<RecyclerView>(R.id.recyclerView)
            listRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@AlunosListaActivity)
                adapter = br.com.fiap.controledealunos.recyclerviews.ListAlunoAdapter(
                    it,
                    this@AlunosListaActivity
                )
            }
        })
    }

    private fun onClickLogout(){
        val btLogout = findViewById<Button>(R.id.bt_logout_alunos_lista)
        btLogout.setOnClickListener {
            val intent = Intent(this@AlunosListaActivity, LogonActivity::class.java)
            startActivity(intent)
            this@AlunosListaActivity.finish()
        }
    }

    private fun onClickNovaPesquisa(){

        val bt = findViewById<Button>(R.id.bt_nova_pesquisa)
        bt.setOnClickListener{
            val intent = Intent(this@AlunosListaActivity, MainActivity::class.java)
            startActivity(intent)
            this@AlunosListaActivity.finish()
        }
    }

    private fun getName(chave: String) : String{
        val extras = intent.extras
        var valueNome = ""
        if (extras != null) {
            valueNome = extras.getString(chave).toString()
        }
        return valueNome
    }

    override fun onNoteClick(position: Int, nome: String) {

        val intent = Intent(this@AlunosListaActivity, DetalhesActivity::class.java)
        intent.putExtra("position", position)
        intent.putExtra("nome", nome.trim())
        intent.putExtra("nomePesquisado", getName("nomePesquisado"))
        startActivity(intent)
    }
}
