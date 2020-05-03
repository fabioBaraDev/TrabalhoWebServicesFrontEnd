package br.com.fiap.controledealunos.recyclerviews

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.controledealunos.R
import br.com.fiap.controledealunos.model.Aluno

class ListAlunoAdapter(private val list: List<Aluno>) : RecyclerView.Adapter<AlunosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlunosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AlunosViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: AlunosViewHolder, position: Int) {
       val aluno : Aluno = list[position]
        holder.bind(aluno)
    }
}

class AlunosViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {

    private var mIDView: TextView? = null
    private var mNomeView: TextView? = null

    init {
        mIDView = itemView.findViewById(R.id.id_aluno)
        mNomeView = itemView.findViewById(R.id.nome)
    }

    fun bind(aluno: Aluno){
        mIDView?.text = aluno.id
        mNomeView?.text = aluno.nome
    }
}