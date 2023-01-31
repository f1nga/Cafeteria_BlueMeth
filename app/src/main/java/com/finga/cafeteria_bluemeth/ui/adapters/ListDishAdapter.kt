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

    @SuppressLint("SetTextI18n")
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
                listener.onItemClick(Dish(textView.text.toString(), preuFinal, categoria = 4))
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

        holder.cantidadView.text = ""
        holder.iconMes.visibility = View.INVISIBLE
        holder.iconMenys.visibility = View.INVISIBLE

        holder.textView.text = item.name
        holder.imgView.setBackgroundResource(searchImage(item.name))
        holder.priceView.text = "${item.price}€"
        item.cantidad = if(holder.cantidadView.text.toString() == "") 0 else holder.cantidadView.text.toString().toInt()
    }

    override fun getItemCount(): Int {
        if(dataset?.size == null){
            return 0
        }
        return dataset!!.size
    }

    private fun searchImage(imgName: String): Int {
        when(imgName) {
            "Macarrons" -> return R.drawable.macarrones
            "Lasagna" -> return R.drawable.lasagna
            "Canelons" -> return R.drawable.canelones
            "Menestre" -> return R.drawable.menestra
            "Entrecot" -> return R.drawable.entrecot
            "Chuletas" -> return R.drawable.chuletas
            "Pollo ast" -> return R.drawable.pollo
            "Pulpito" -> return R.drawable.pulpo
            "Natillas" -> return R.drawable.natillas
            "Sorbette" -> return R.drawable.sorbette
            "Calippo" -> return R.drawable.calippo
            "Limonada" -> return R.drawable.limonada
        }
        return -1
    }
}