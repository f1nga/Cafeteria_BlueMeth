package com.finga.cafeteria_bluemeth.ui.pages.home.tabs

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.ui.adapters.ListDishAdapter
import com.finga.cafeteria_bluemeth.databinding.FragmentThirdDishBinding
import com.finga.cafeteria_bluemeth.data.models.Dish
import com.finga.cafeteria_bluemeth.ui.pages.faqs.FaqsActivity
import com.finga.cafeteria_bluemeth.ui.pages.login.LoginActivity
import com.finga.cafeteria_bluemeth.ui.pages.my_profile.MyProfileActivity
import com.finga.cafeteria_bluemeth.ui.viewmodels.BillViewModel
import com.finga.cafeteria_bluemeth.ui.viewmodels.DishViewModel
import com.finga.cafeteria_bluemeth.ui.viewmodels.UserViewModel

class ThirdDishFragment : Fragment() {
    private val dishViewModel: DishViewModel by activityViewModels()
    private val billViewModel: BillViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()
    lateinit var listDishAdapter: ListDishAdapter
    lateinit var binding: FragmentThirdDishBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_third_dish, container, false
        )
        setHasOptionsMenu(true)

        setRecyclerView()

        return binding.root
    }

    private fun setRecyclerView() {
        dishViewModel.getDishesByCategory(requireContext(), 3).observe(viewLifecycleOwner) {
            listDishAdapter = ListDishAdapter(it)
            val recyclerView = binding.RecyclerView

            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = listDishAdapter

            clickToDish()
        }
    }

    private fun clickToDish() {
        listDishAdapter.setOnItemClickListener(object: ListDishAdapter.onItemClickListener{
            override fun onItemClick(plat: Dish) {
                if(userViewModel.userIsLogged()) {
                    billViewModel.addDishToBill(plat)
                    Log.i("Third Dish", billViewModel.getPlatsFromBill().toString())
                } else {
                    notLoginAlert()
                }
            }
        })
    }

    private fun notLoginAlert() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Para realizar estas acciones debe iniciar sesión")
            .setCancelable(false)
            .setPositiveButton("INICIAR SESION") { dialog, _ ->
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
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

        if (id == R.id.action_my_profile){
            if(userViewModel.userIsLogged() ) {
                val intent = Intent(requireContext(), MyProfileActivity::class.java)
                intent.putExtra("user_nickname", userViewModel.getCurrentUser()?.nickname)
                intent.putExtra("user_email", userViewModel.getCurrentUser()?.email)
                startActivity(intent)
            } else {
                notLoginAlert()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}