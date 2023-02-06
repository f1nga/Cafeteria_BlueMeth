package com.finga.cafeteria_bluemeth.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.data.models.Dish
import com.finga.cafeteria_bluemeth.utils.Methods


class ListDishAdapter(
    private val dataset: List<Dish>? = null
) : RecyclerView.Adapter<ListDishAdapter.ItemViewHolder>() {

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener {
        fun onItemClick(plat: Dish)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    class ItemViewHolder(view: View, listener: onItemClickListener) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.idNombre)
        var imgView: ImageView = view.findViewById(R.id.idImagen)
        var priceView: TextView = view.findViewById(R.id.idPrecio)

        init {
            itemView.setOnClickListener {
                val preuFinal = priceView.text.split("€")[0].toInt()
                listener.onItemClick(Dish(textView.text.toString(), preuFinal, categoria = 4))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_list, parent, false)
        return ItemViewHolder(adapterLayout, mListener)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset?.get(position) ?: return

        holder.textView.text = item.name
        holder.imgView.setBackgroundResource(Methods.searchDishImage(item.name))
        holder.priceView.text = "${item.price}€"
    }

    override fun getItemCount(): Int {
        if(dataset?.size == null){
            return 0
        }
        return dataset.size
    }
}