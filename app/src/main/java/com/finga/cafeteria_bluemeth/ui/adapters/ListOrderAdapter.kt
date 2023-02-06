package com.finga.cafeteria_bluemeth.ui.adapters


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.data.models.Order
import com.finga.cafeteria_bluemeth.utils.Methods


class ListOrderAdapter : ListAdapter<Order, ListOrderAdapter.OrderHolder>(DiffCallback()) {

    class OrderHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.order_list, parent, false)
        return OrderHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: OrderHolder, position: Int) {
        val currentItem = getItem(position)

        val firstDishName = holder.itemView.findViewById<TextView>(R.id.txt_primer_plat)
        val secondDishName = holder.itemView.findViewById<TextView>(R.id.txt_segon_plat)
        val thirdDishName = holder.itemView.findViewById<TextView>(R.id.txt_tercer_plat)

        val firstDishImg = holder.itemView.findViewById<ImageView>(R.id.img_primer_plat)
        val secondDishImg = holder.itemView.findViewById<ImageView>(R.id.img_segon_plat)
        val thirdDishImg = holder.itemView.findViewById<ImageView>(R.id.img_tercer_plat)

        val firstDishPrice = holder.itemView.findViewById<TextView>(R.id.txt_preu_primer_plat)
        val secondDishPrice = holder.itemView.findViewById<TextView>(R.id.txt_preu_segon_plat)
        val thirdDishPrice = holder.itemView.findViewById<TextView>(R.id.txt_preu_tercer_plat)

        val totalPrice = holder.itemView.findViewById<TextView>(R.id.txt_preu_total)

        val currentDate = holder.itemView.findViewById<TextView>(R.id.txtData)

        firstDishName.text = currentItem.firstDish
        secondDishName.text = currentItem.secondDish
        thirdDishName.text = currentItem.thirdDish

        firstDishImg.setBackgroundResource(Methods.searchDishImage(currentItem.firstDish))
        secondDishImg.setBackgroundResource(Methods.searchDishImage(currentItem.secondDish))
        thirdDishImg.setBackgroundResource(Methods.searchDishImage(currentItem.thirdDish))

        val firstPrice = Methods.searchDishPrice(currentItem.firstDish)
        val secondPrice = Methods.searchDishPrice(currentItem.secondDish)
        val thirdPrice = Methods.searchDishPrice(currentItem.thirdDish)

        firstDishPrice.text = "$firstPrice€"
        secondDishPrice.text =  "$secondPrice€"
        thirdDishPrice.text = "$thirdPrice€"

        totalPrice.text = "${(firstPrice + secondPrice + thirdPrice)}€"

        currentDate.text = currentItem.date
    }

    class DiffCallback : DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(oldItem: Order, newItem: Order) =
            oldItem.firstDish == newItem.firstDish

        override fun areContentsTheSame(oldItem: Order, newItem: Order) =
            oldItem == newItem
    }
}