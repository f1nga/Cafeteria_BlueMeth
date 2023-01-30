package com.finga.cafeteria_bluemeth.adapters

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
import com.finga.cafeteria_bluemeth.models.Dish
import com.finga.cafeteria_bluemeth.ui.pages.home.controllers.BillFragmentController


class DishAdapter : ListAdapter<Dish, DishAdapter.DishHolder>(DiffCallback()) {

    class DishHolder(view: View) : RecyclerView.ViewHolder(view)

    private lateinit var listener: RecyclerClickListener

    fun setItemListener(listener: RecyclerClickListener) {
        this.listener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.card_list, parent, false)

        val dishHolder = DishHolder(v)

        val dishClick = dishHolder.itemView.findViewById<CardView>(R.id.cardView)

        dishClick.setOnClickListener {
            listener.onItemClick(dishHolder.adapterPosition)
        }

        return dishHolder
    }

    override fun onBindViewHolder(holder: DishHolder, position: Int) {
        val currentItem = getItem(position)

        val dishName = holder.itemView.findViewById<TextView>(R.id.idNombre)
        val dishPrice = holder.itemView.findViewById<TextView>(R.id.idPrecio)
        val dishImg = holder.itemView.findViewById<ImageView>(R.id.idImagen)
        val dishQuantity = holder.itemView.findViewById<TextView>(R.id.idQuantitat)

        if(currentItem.cantidad == 0) {
            dishQuantity.text = ""
            holder.itemView.findViewById<ImageView>(R.id.idIconMes).visibility = View.INVISIBLE
            holder.itemView.findViewById<ImageView>(R.id.idIconMenys).visibility = View.INVISIBLE

        }
        else dishQuantity.text  = currentItem.cantidad.toString()


        dishName.text = currentItem.name
        dishPrice.text = "${currentItem.price}€"
        dishImg.setBackgroundResource(BillFragmentController().searchImage(currentItem.name))
        currentItem.cantidad = if(dishQuantity.text == "") 0 else dishQuantity.text.toString().toInt()

    }

    class DiffCallback : DiffUtil.ItemCallback<Dish>() {
        override fun areItemsTheSame(oldItem: Dish, newItem: Dish) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Dish, newItem: Dish) =
            oldItem == newItem
    }
}