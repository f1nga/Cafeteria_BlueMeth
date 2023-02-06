package com.finga.cafeteria_bluemeth.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.data.models.Dish
import com.finga.cafeteria_bluemeth.ui.adapters.utils.RecyclerClickListener
import com.finga.cafeteria_bluemeth.utils.Methods


class NewListDishAdapter : ListAdapter<Dish, NewListDishAdapter.DishHolder>(DiffCallback()) {

    class DishHolder(view: View) : RecyclerView.ViewHolder(view)

    private lateinit var listener: RecyclerClickListener

    fun setItemListener(listener: RecyclerClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.card_list, parent, false)

        val dishHolder = DishHolder(v)

        val itemClick = dishHolder.itemView.findViewById<CardView>(R.id.cardView)
        itemClick.setOnClickListener {
            listener.onItemClick(dishHolder.adapterPosition)
        }

        return dishHolder
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DishHolder, position: Int) {
        val currentItem = getItem(position)

        val dishName = holder.itemView.findViewById<TextView>(R.id.idNombre)
        val dishPrice = holder.itemView.findViewById<TextView>(R.id.idPrecio)
        val dishImg = holder.itemView.findViewById<ImageView>(R.id.idImagen)

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