package com.finga.cafeteria_bluemeth.ui.pages.home.tabs

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.adapters.ListDishAdapter
import com.finga.cafeteria_bluemeth.data.DataFishes
import com.finga.cafeteria_bluemeth.databinding.FragmentSecondFishBinding

class SecondDishFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSecondFishBinding>(
            inflater,
            R.layout.fragment_second_fish, container, false
        )
        setHasOptionsMenu(true)

        val myDataset = DataFishes().secondDishes()

        val recyclerView = binding.RecyclerView
        val manager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = manager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = ListDishAdapter(requireContext(), myDataset)
        return binding.root
    }

    //enable options menu in this fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
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
        if (id == R.id.action_settings){
            //do your action here, im just showing toast
            Toast.makeText(activity, "Settings", Toast.LENGTH_SHORT).show()
        }
        if (id == R.id.action_sort){
            //do your action here, im just showing toast
            Toast.makeText(activity, "Sort", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }
}