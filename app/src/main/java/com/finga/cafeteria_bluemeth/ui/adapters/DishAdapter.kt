package com.finga.cafeteria_bluemeth.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.data.models.Dish
import com.finga.cafeteria_bluemeth.utils.Methods


class DishAdapter : ListAdapter<Dish, DishAdapter.DishHolder>(DiffCallback()) {

    class DishHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.card_list, parent, false)

        val dishHolder = DishHolder(v)

        val iconMes = dishHolder.itemView.findViewById<ImageView>(R.id.idIconMes)
        val iconMenys = dishHolder.itemView.findViewById<ImageView>(R.id.idIconMenys)
        val numQuantitat = dishHolder.itemView.findViewById<TextView>(R.id.idQuantitat)

        iconMes.setOnClickListener {
            numQuantitat.text =  (numQuantitat.text.toString().toInt() + 1).toString()
        }

        iconMenys.setOnClickListener {
            if(numQuantitat.text.toString().toInt() >= 1) {
                numQuantitat.text =  (numQuantitat.text.toString().toInt() - 1).toString()
            }
        }

        return dishHolder
    }

    override fun onBindViewHolder(holder: DishHolder, position: Int) {
        val currentItem = getItem(position)

        val dishName = holder.itemView.findViewById<TextView>(R.id.idNombre)
        val dishPrice = holder.itemView.findViewById<TextView>(R.id.idPrecio)
        val dishImg = holder.itemView.findViewById<ImageView>(R.id.idImagen)
        val dishQuantity = holder.itemView.findViewById<TextView>(R.id.idQuantitat)

        dishQuantity.text  = currentItem.cantidad.toString()
        dishName.text = currentItem.name
        dishPrice.text = "${currentItem.price}€"
        dishImg.setBackgroundResource(Methods.searchDishImage(currentItem.name))
    }

    class DiffCallback : DiffUtil.ItemCallback<Dish>() {
        override fun areItemsTheSame(oldItem: Dish, newItem: Dish) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Dish, newItem: Dish) =
            oldItem == newItem
    }
}