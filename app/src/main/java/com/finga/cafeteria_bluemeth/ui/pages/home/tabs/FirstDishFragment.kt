package com.finga.cafeteria_bluemeth.ui.pages.home.tabs

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.adapters.ListDishAdapter
import com.finga.cafeteria_bluemeth.databinding.FragmentFirstDishBinding
import com.finga.cafeteria_bluemeth.models.Dish
import com.finga.cafeteria_bluemeth.ui.pages.faqs.FaqsActivity
import com.finga.cafeteria_bluemeth.ui.pages.home.HomeActivity
import com.finga.cafeteria_bluemeth.ui.pages.login.LoginActivity
import com.finga.cafeteria_bluemeth.viewmodel.DishViewModel
import com.finga.cafeteria_bluemeth.viewmodel.UserViewModel

class FirstDishFragment : Fragment() {
    private lateinit var dishViewModel: DishViewModel
    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var sm : SendDish
    lateinit var recyclerView: RecyclerView
    lateinit var listDishAdapter: ListDishAdapter
    lateinit var binding: FragmentFirstDishBinding
    var email: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_first_dish, container, false
        )
        setHasOptionsMenu(true)

        dishViewModel = ViewModelProvider(this)[DishViewModel::class.java]
        setRecyclerView()

        return binding.root
    }

    private fun setRecyclerView() {
        dishViewModel.getDishesByCategory(requireContext(), 1).observe(viewLifecycleOwner, Observer {
            listDishAdapter = ListDishAdapter(it)
            recyclerView = binding.RecyclerView

            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = listDishAdapter

            sendDataToBillFragment()
        })
    }

    private fun sendDataToBillFragment() {
        sm = activity as SendDish

        listDishAdapter.setOnItemClickListener(object: ListDishAdapter.onItemClickListener{
            override fun onItemClick(plat: Dish) {
                sm.sendDataToBillFragment(plat)
                Toast.makeText(requireContext(), email, Toast.LENGTH_SHORT).show()
            }
        })
    }

    //inflate the menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    //handle item clicks of menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //get item id to handle item clicks
        val id = item.itemId
        //handle item clicks
        if (id == R.id.action_faqs){
            val intent = Intent(requireContext(), FaqsActivity::class.java)
            startActivity(intent)
        }
        if (id == R.id.action_exit){
            //do your action here, im just showing toast
            Toast.makeText(activity, "Sort", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }
}