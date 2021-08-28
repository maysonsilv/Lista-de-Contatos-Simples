package com.mayson.listadecontatossimples

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter: RecyclerView.Adapter<ContactAdapter.ContactViewHolder>(){

    private val list: MutableList<Contact> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
       var view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)

       return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.find(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun find(contact: Contact){}
    }


}