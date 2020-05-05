package br.com.fiap.controledealunos.recyclerviews

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.controledealunos.R
import br.com.fiap.controledealunos.model.Aluno
import br.com.fiap.controledealunos.view.alunos.AlunosListaActivity


class ListAlunoAdapter(
    private val list: List<Aluno>,
    private val onNoteListener: AlunosViewHolder.OnNoteListener
) :
    RecyclerView.Adapter<AlunosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlunosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AlunosViewHolder(inflater, parent, onNoteListener)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: AlunosViewHolder, position: Int) {
        val aluno: Aluno = list[position]
        holder.bind(aluno)
    }
}

class AlunosViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup,
    onNoteListener: OnNoteListener
) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)),
    View.OnClickListener {

    private var mIDView: TextView? = null
    private var mNomeView: TextView? = null
    private var mOnNoteListener: OnNoteListener

    init {
        mIDView = itemView.findViewById(R.id.id_aluno)
        mNomeView = itemView.findViewById(R.id.nome)
        mOnNoteListener = onNoteListener

        itemView.setOnClickListener(this)
    }

    fun bind(aluno: Aluno) {
        mIDView?.text = aluno.id
        mNomeView?.text = aluno.nome
    }

    override fun onClick(v: View?) {
        Log.d(TAG, "Entrou" + mIDView?.text)

        mOnNoteListener.onNoteClick( mIDView?.text.toString().toInt(), mNomeView?.text.toString());
    }

    interface OnNoteListener {
        fun onNoteClick(position: Int, nomePesquisado: String)
    }
}

