package br.com.fiap.controledealunos.view.alunos

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ListAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.controledealunos.R
import br.com.fiap.controledealunos.list.ListAlunosViewModel
import br.com.fiap.controledealunos.recyclerviews.AlunosViewHolder
import br.com.fiap.controledealunos.recyclerviews.ListAlunoAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class AlunosListaActivity : AppCompatActivity(), AlunosViewHolder.OnNoteListener {

    val listAlunosViewModel: ListAlunosViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alunos_lista)

        listAlunosViewModel.getAlunos()

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

    override fun onNoteClick(position: Int, nome: String) {
        val intent = Intent(this@AlunosListaActivity, DetalhesActivity::class.java)
        intent.putExtra("position", position)
        intent.putExtra("nome", nome.trim())
        startActivity(intent)
    }
}
