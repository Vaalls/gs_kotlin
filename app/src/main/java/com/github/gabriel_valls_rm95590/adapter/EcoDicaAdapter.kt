package com.github.gabriel_valls_rm95590.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.gabriel_valls_rm95590.model.EcoDica

class EcoDicaAdapter(
    private val listaEcoDicas: List<EcoDica>,
    private val onItemClick: (EcoDica) -> Unit
) : RecyclerView.Adapter<EcoDicaAdapter.EcoDicaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EcoDicaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_2, parent, false)
        return EcoDicaViewHolder(view)
    }

    override fun onBindViewHolder(holder: EcoDicaViewHolder, position: Int) {
        val ecoDica = listaEcoDicas[position]
        holder.titulo.text = ecoDica.titulo
        holder.descricao.text = ecoDica.descricao
        holder.itemView.setOnClickListener { onItemClick(ecoDica) }
    }

    override fun getItemCount(): Int {
        return listaEcoDicas.size
    }

    class EcoDicaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo: TextView = view.findViewById(android.R.id.text1)
        val descricao: TextView = view.findViewById(android.R.id.text2)
    }
}