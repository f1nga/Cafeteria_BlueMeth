package com.finga.cafeteria_bluemeth.ui.pages.home.tabs

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.databinding.FragmentBillBinding
import com.finga.cafeteria_bluemeth.data.models.Order
import com.finga.cafeteria_bluemeth.ui.adapters.DishAdapter
import com.finga.cafeteria_bluemeth.ui.pages.faqs.FaqsActivity
import com.finga.cafeteria_bluemeth.ui.pages.home.HomeActivity
import com.finga.cafeteria_bluemeth.ui.pages.login.LoginActivity
import com.finga.cafeteria_bluemeth.ui.pages.my_profile.MyProfileActivity
import com.finga.cafeteria_bluemeth.ui.viewmodels.BillViewModel
import com.finga.cafeteria_bluemeth.ui.viewmodels.OrderViewModel
import com.finga.cafeteria_bluemeth.ui.viewmodels.UserViewModel
import java.time.LocalDate

class BillFragment : Fragment() {
    lateinit var listDishAdapter: DishAdapter
    private lateinit var orderViewModel: OrderViewModel
    private val billViewModel: BillViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var binding: FragmentBillBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_bill, container, false
        )
        setHasOptionsMenu(true)

        orderViewModel = ViewModelProvider(this)[OrderViewModel::class.java]

        Log.i("Bill Fragment", billViewModel.getPlatsFromBill().toString())

        setRecyclerView()
        observeDishes()
        pay()

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun pay() {
       binding.btnPagar.setOnClickListener() {
           if(userViewModel.userIsLogged()) {
               val maxId = orderViewModel.getMaxId(requireContext())
               val currentDate = LocalDate.now()
               val order = Order(billViewModel.listPlats[0].name, billViewModel.listPlats[1].name, billViewModel.listPlats[2].name, currentDate.toString(), userViewModel.getCurrentUser()!!.email,  maxId + 1)

               orderViewModel.addOrder(requireContext(), order)

               payAlert()
           } else {
               notLoginAlert()
           }
       }
    }

    private fun payAlert() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Has realitzat la compra")
            .setCancelable(false)
            .setPositiveButton("SEGUIR COMPRANDO") { dialog, _ ->
                val intent = Intent(requireContext(), HomeActivity::class.java)
                intent.putExtra("user_nickname", userViewModel.getCurrentUser()!!.nickname)
                intent.putExtra("user_email", userViewModel.getCurrentUser()!!.email)
                intent.putExtra("user_password", userViewModel.getCurrentUser()!!.password)
                startActivity(intent)
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }

    private fun setRecyclerView() {
        val itemsRecyclerView = binding.RecyclerView
        itemsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        itemsRecyclerView.setHasFixedSize(true)
        listDishAdapter = DishAdapter()

        itemsRecyclerView.adapter = listDishAdapter
    }

    private fun observeDishes() {
        Log.i("Observe", billViewModel.getPlatsFromBill().toString())
        listDishAdapter.submitList(billViewModel.getPlatsFromBill())
        binding.txtPreu.text = "${billViewModel.getPreu()}€"
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater!!.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

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