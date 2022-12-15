package com.finga.cafeteria_bluemeth.ui.pages.home.tabs

import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.adapters.ListDishAdapter
import com.finga.cafeteria_bluemeth.databinding.FragmentBillBinding
import com.finga.cafeteria_bluemeth.models.Dish
import com.finga.cafeteria_bluemeth.ui.pages.home.controllers.BillFragmentController
import com.finga.cafeteria_bluemeth.viewmodel.DishViewModel

class BillFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var listDishAdapter: ListDishAdapter
    var listPlats: ArrayList<Dish> = ArrayList()
    private lateinit var txtPreu: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentBillBinding>(
            inflater,
            R.layout.fragment_bill, container, false
        )
        setHasOptionsMenu(true)

        recyclerView = binding.RecyclerView
        txtPreu = binding.txtPreu

        updateRecycler(BillFragmentController().updatePrice(listPlats))
        return binding.root
    }

    fun displayReceivedData(plat: Dish) {
        var dish: Dish? = BillFragmentController().dishExists(listPlats, plat)

        if(dish != null) dish.cantidad += 1
        else listPlats.add(plat)

        updateRecycler(BillFragmentController().updatePrice(listPlats))
    }

    private fun updateRecycler(preuFinal: Int) {
        txtPreu.text = "$preuFinal€"

        val myDataset = listPlats
        listDishAdapter = ListDishAdapter(myDataset)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = listDishAdapter

        listDishAdapter.setOnItemClickListener(object: ListDishAdapter.onItemClickListener{
            override fun onItemClick(plat: Dish) {
            }
        })
    }

    //inflate the menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater!!.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    //handle item clicks of menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //get item id to handle item clicks
        val id = item!!.itemId
        //handle item clicks
        if (id == R.id.action_settings) {
            //do your action here, im just showing toast
            Toast.makeText(activity, "Settings", Toast.LENGTH_SHORT).show()
        }
        if (id == R.id.action_sort) {
            //do your action here, im just showing toast
            Toast.makeText(activity, "Sort", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }


}