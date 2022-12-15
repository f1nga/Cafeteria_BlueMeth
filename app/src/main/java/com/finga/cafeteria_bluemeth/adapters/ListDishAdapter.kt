package com.finga.cafeteria_bluemeth.adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.TypedArrayUtils.getText
import androidx.recyclerview.widget.RecyclerView
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.models.Dish
import com.finga.cafeteria_bluemeth.ui.pages.home.controllers.BillFragmentController
import com.finga.cafeteria_bluemeth.ui.pages.home.tabs.SendDish

class ListDishAdapter(
    private val dataset: ArrayList<Dish>? = null
) : RecyclerView.Adapter<ListDishAdapter.ItemViewHolder>() {

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener {
        fun onItemClick(plat: Dish)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    class ItemViewHolder(private val view: View, listener: onItemClickListener) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.idNombre)
        var imgView: ImageView = view.findViewById(R.id.idImagen)
        var priceView: TextView = view.findViewById(R.id.idPrecio)
        var cantidadView: TextView = view.findViewById(R.id.idQuantitat)
        var iconMes: ImageView = view.findViewById(R.id.idIconMes)
        var iconMenys: ImageView = view.findViewById(R.id.idIconMenys)

        init {
            itemView.setOnClickListener {
                val preuFinal = priceView.text.split("€")[0].toInt()
                listener.onItemClick(Dish(textView.text.toString(), preuFinal, BillFragmentController().searchImage(textView.text.toString()), 1))
            }

            iconMes.setOnClickListener()  {
                cantidadView.text = BillFragmentController().add(cantidadView).toString()
            }

            iconMenys.setOnClickListener()  {
                cantidadView.text = BillFragmentController().remove(cantidadView).toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_list, parent, false)
        return ItemViewHolder(adapterLayout, mListener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset?.get(position) ?: return

        if(item.cantidad == 0) {
            holder.cantidadView.text = ""
            holder.iconMes.visibility = View.INVISIBLE
            holder.iconMenys.visibility = View.INVISIBLE
        }
        else holder.cantidadView.text = item.cantidad.toString()
        holder.textView.text = item.name
        holder.imgView.setBackgroundResource(item.image)
        holder.priceView.text = "${item.price}€"


    }


    override fun getItemCount(): Int {
        if(dataset?.size == null){
            return 0
        }
        return dataset!!.size
    }



}

