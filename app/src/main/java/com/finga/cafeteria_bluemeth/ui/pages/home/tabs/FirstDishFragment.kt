package com.finga.cafeteria_bluemeth.ui.pages.home.tabs

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.ui.adapters.ListDishAdapter
import com.finga.cafeteria_bluemeth.databinding.FragmentFirstDishBinding
import com.finga.cafeteria_bluemeth.data.models.Dish
import com.finga.cafeteria_bluemeth.data.models.User
import com.finga.cafeteria_bluemeth.ui.pages.faqs.FaqsActivity
import com.finga.cafeteria_bluemeth.ui.pages.home.HomeActivity
import com.finga.cafeteria_bluemeth.ui.pages.login.LoginActivity
import com.finga.cafeteria_bluemeth.ui.pages.my_profile.MyProfileActivity
import com.finga.cafeteria_bluemeth.ui.viewmodels.BillViewModel
import com.finga.cafeteria_bluemeth.ui.viewmodels.DishViewModel
import com.finga.cafeteria_bluemeth.ui.viewmodels.UserViewModel

class FirstDishFragment(private val userEmail: String, private val userPassword: String, private val userNickname: String) : Fragment() {
    private val dishViewModel: DishViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()
    private val billViewModel: BillViewModel by activityViewModels()
    lateinit var recyclerView: RecyclerView
    lateinit var listDishAdapter: ListDishAdapter
    lateinit var binding: FragmentFirstDishBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_first_dish, container, false
        )
        setHasOptionsMenu(true)

        setRecyclerView()
        setUser()

        return binding.root
    }

    private fun setRecyclerView() {
        dishViewModel.getDishesByCategory(requireContext(), 1).observe(viewLifecycleOwner, Observer {
            listDishAdapter = ListDishAdapter(it)
            recyclerView = binding.RecyclerView

            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = listDishAdapter

            clickToDish()
        })
    }

    private fun clickToDish() {
        listDishAdapter.setOnItemClickListener(object: ListDishAdapter.onItemClickListener{
            override fun onItemClick(plat: Dish) {
                if(userViewModel.userIsLogged()) {
                    billViewModel.addDishToBill(plat)
                    Log.i("First Dish", billViewModel.getPlatsFromBill().toString())
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

    private fun setUser() {
        if(userEmail != "") {
            userViewModel.setCurrentUser(User(userNickname, userEmail, userPassword))
        }
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
            intent.putExtra("user_email", userEmail)
            startActivity(intent)
        }
        if (id == R.id.action_my_profile){
            val intent = Intent(requireContext(), MyProfileActivity::class.java)
            intent.putExtra("user_nickname", userNickname)
            intent.putExtra("user_email", userEmail)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }
}