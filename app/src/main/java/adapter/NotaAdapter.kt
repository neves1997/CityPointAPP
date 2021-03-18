package adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dataclasse.Notasdc
import entities.Notaent
import ipvc.estg.citypointecgm.R

class NotaAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<NotaAdapter.NotaViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var notaent = emptyList<Notaent>()

    class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var notatitle: TextView = itemView.findViewById(R.id.titulo)
        var notatexto: TextView = itemView.findViewById(R.id.texto)
        var notadata: TextView = itemView.findViewById(R.id.data)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
       val itemView = inflater.inflate(R.layout.recyclerline,parent,false)
        return NotaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val current = notaent[position]

        holder.notatitle.text = current.titulo
        holder.notatexto.text = current.texto
        holder.notadata.text = current.data
    }

    internal fun setNotas(notaent: List<Notaent>) {
        this.notaent = notaent
        notifyDataSetChanged()
    }

    override fun getItemCount() = notaent.size

}