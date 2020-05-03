package br.com.fiap.controledealunos.view.alunos

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.controledealunos.R
import br.com.fiap.controledealunos.list.ListAlunosViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class AlunosListaActivity : AppCompatActivity() {

    val listAlunosViewModel: ListAlunosViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alunos_lista)

        listAlunosViewModel.getAlunos()

        listAlunosViewModel.messageError.observe(this, Observer {
            if(it != "") {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })

        listAlunosViewModel.alunos.observe(this, Observer {
            val listRecyclerView = findViewById<RecyclerView>(R.id.recyclerView)
            listRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@AlunosListaActivity)
                adapter = br.com.fiap.controledealunos.recyclerviews.ListAlunoAdapter(it)
            }
        })
    }
}
