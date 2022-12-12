package com.finga.cafeteria_bluemeth.ui.pages.home.tabs

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.adapters.ListDishAdapter
import com.finga.cafeteria_bluemeth.databinding.FragmentBillBinding
import com.finga.cafeteria_bluemeth.models.Dish
import com.finga.cafeteria_bluemeth.viewmodel.DishViewModel

class BillFragment : Fragment() {
    private val dishViewModel: DishViewModel by viewModels()
    private lateinit var sm : SendDish
    lateinit var recyclerView: RecyclerView
    lateinit var listDishAdapter: ListDishAdapter
    var listPlats: ArrayList<Dish> = ArrayList()

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

        val myDataset = listPlats
        listDishAdapter = ListDishAdapter(myDataset)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = listDishAdapter

        listDishAdapter.setOnItemClickListener(object: ListDishAdapter.onItemClickListener{
            override fun onItemClick(plat: Dish) {

            }
        })

        return binding.root
    }

    fun displayReceivedData(plat: Dish) {
        listPlats.add(plat)

        val myDataset = listPlats
        listDishAdapter = ListDishAdapter(myDataset)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = listDishAdapter
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