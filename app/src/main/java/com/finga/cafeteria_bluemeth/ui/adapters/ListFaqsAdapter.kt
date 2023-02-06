package com.finga.cafeteria_bluemeth.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.data.models.Faq

class ListFaqsAdapter(
    private val dataset: List<Faq>? = null
) : RecyclerView.Adapter<ListFaqsAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtTitol: TextView = view.findViewById(R.id.txtTitol)
        var txtResposta: TextView = view.findViewById(R.id.txtResposta)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.faqs_list, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset?.get(position) ?: return

        holder.txtTitol.text = item.question
        holder.txtResposta.text = item.answer
    }

    override fun getItemCount(): Int {
        if(dataset?.size == null){
            return 0
        }
        return dataset.size
    }
}