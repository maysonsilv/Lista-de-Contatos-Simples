package com.mayson.listadecontatossimples

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(var listener: ClickItemContactListener): RecyclerView.Adapter<ContactAdapter.ContactAdapterViewHolder>(){

    private val list: MutableList<Contact> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapterViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)

       return ContactAdapterViewHolder(view, list, listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    override fun onBindViewHolder(holder: ContactAdapterViewHolder, position: Int) {
        holder.bind(list[position])
    }


    fun updateList(list: List<Contact>){
        this.list.clear()
        this.list.addAll(list)

    }

    class ContactAdapterViewHolder(itemView: View, var list: List<Contact>, var listener: ClickItemContactListener) : RecyclerView.ViewHolder(itemView){

        private val tvNome: TextView = itemView.findViewById(R.id.tv_nome)
        private val tvTelefone: TextView = itemView.findViewById(R.id.tv_telefone)
        private val tvImagem: ImageView = itemView.findViewById(R.id.iv_imagem)

        init {
            itemView.setOnClickListener{
                listener.clickItemContact(list[adapterPosition])
            }
        }

        fun bind(contact: Contact){
            tvNome.text = contact.nome
            tvTelefone.text = contact.telefone

        }
    }


}