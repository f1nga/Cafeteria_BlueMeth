package com.finga.cafeteria_bluemeth.ui.pages.home.tabs

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.adapters.ListDishAdapter
import com.finga.cafeteria_bluemeth.databinding.FragmentThirdDishBinding
import com.finga.cafeteria_bluemeth.models.Dish
import com.finga.cafeteria_bluemeth.ui.pages.faqs.FaqsActivity
import com.finga.cafeteria_bluemeth.viewmodel.DishViewModel

class ThirdDishFragment : Fragment() {
    private lateinit var dishViewModel: DishViewModel
    lateinit var listDishAdapter: ListDishAdapter
    private lateinit var sm : SendDish
    lateinit var binding: FragmentThirdDishBinding
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_third_dish, container, false
        )
        setHasOptionsMenu(true)

        dishViewModel = ViewModelProvider(this)[DishViewModel::class.java]

        setRecyclerView()

        return binding.root
    }

    private fun setRecyclerView() {
        dishViewModel.getDishesByCategory(requireContext(), 3).observe(viewLifecycleOwner) {
            listDishAdapter = ListDishAdapter(it)
            recyclerView = binding.RecyclerView

            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = listDishAdapter

            sendDataToBillFragment()
        }
    }

    private fun sendDataToBillFragment() {
        sm = activity as SendDish

        listDishAdapter.setOnItemClickListener(object: ListDishAdapter.onItemClickListener{
            override fun onItemClick(plat: Dish) {
                sm.sendDataToBillFragment(plat)
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
            val id = item.itemId

        if (id == R.id.action_faqs) {
            val intent = Intent(requireContext(), FaqsActivity::class.java)
            startActivity(intent)
        }

        if (id == R.id.action_exit) {
            Toast.makeText(activity, "Sort", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }
}