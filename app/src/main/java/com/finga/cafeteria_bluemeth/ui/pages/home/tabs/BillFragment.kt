package com.finga.cafeteria_bluemeth.ui.pages.home.tabs

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.adapters.ListDishAdapter
import com.finga.cafeteria_bluemeth.databinding.FragmentBillBinding
import com.finga.cafeteria_bluemeth.models.Dish
import com.finga.cafeteria_bluemeth.ui.pages.faqs.FaqsActivity
import com.finga.cafeteria_bluemeth.ui.pages.home.HomeActivity
import com.finga.cafeteria_bluemeth.ui.pages.home.controllers.BillFragmentController
import com.finga.cafeteria_bluemeth.ui.pages.login.LoginActivity
import com.finga.cafeteria_bluemeth.viewmodel.BillViewModel

class BillFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var listDishAdapter: ListDishAdapter
    var listPlats: ArrayList<Dish> = ArrayList()
    private lateinit var txtPreu: TextView
    private lateinit var binding: FragmentBillBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_bill, container, false
        )
        setHasOptionsMenu(true)

        updateRecycler(BillFragmentController().updatePrice(listPlats))
        pay()

        return binding.root
    }

    fun displayReceivedData(plat: Dish) {
        var dish: Dish? = BillFragmentController().dishExists(listPlats, plat)

        if(dish != null) dish.cantidad += 1
        else listPlats.add(plat)

        updateRecycler(BillFragmentController().updatePrice(listPlats))
    }

    private fun updateRecycler(preuFinal: Int) {
        binding.txtPreu.text = "$preuFinal€"

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
    }

    private fun pay() {
        binding.btnPagar.setOnClickListener() {
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("Has realitzat la compra")
                .setCancelable(false)
                .setPositiveButton("SEGUIR COMPRANDO") { dialog, _ ->
                    val intent = Intent(requireContext(), HomeActivity::class.java)
                    startActivity(intent)
                    dialog.dismiss()
                }


            val alert = builder.create()
            alert.show()
        }
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
        if (id == R.id.action_faqs) {
            val intent = Intent(requireContext(), FaqsActivity::class.java)
            startActivity(intent)
        }
        if (id == R.id.action_exit) {
            //do your action here, im just showing toast
            Toast.makeText(activity, "Sort", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }


}