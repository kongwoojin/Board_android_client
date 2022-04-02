package com.kongjak.board_client.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kongjak.board_client.R
import com.kongjak.board_client.data.Board

class BoardAdapter(private val arrayList: ArrayList<Board>) :
    RecyclerView.Adapter<BoardAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView =
            LayoutInflater.from(parent.context).inflate(R.layout.board_list, parent, false)
        return ViewHolder(rootView)
    }

    interface ItemClick {
        fun onClick(view: View, position: Int, id: Int)
    }

    var itemClick: ItemClick? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = arrayList[position]
        holder.title.text = data.title
        holder.username.text = data.username

        holder.itemView.setOnClickListener { v ->
            itemClick?.onClick(v, position, data.id)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.item_title)
        val username: TextView = itemView.findViewById(R.id.item_writer)
    }
}