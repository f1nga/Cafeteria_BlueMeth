package com.finga.cafeteria_bluemeth.ui.pages.home.tabs

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.data.models.Dish
import com.finga.cafeteria_bluemeth.data.models.Order
import com.finga.cafeteria_bluemeth.databinding.FragmentBillBinding
import com.finga.cafeteria_bluemeth.ui.adapters.NewListDishAdapter
import com.finga.cafeteria_bluemeth.ui.adapters.utils.RecyclerClickListener
import com.finga.cafeteria_bluemeth.ui.pages.faqs.FaqsActivity
import com.finga.cafeteria_bluemeth.ui.pages.home.HomeActivity
import com.finga.cafeteria_bluemeth.ui.pages.login.LoginActivity
import com.finga.cafeteria_bluemeth.ui.pages.my_profile.MyProfileActivity
import com.finga.cafeteria_bluemeth.ui.viewmodels.BillViewModel
import com.finga.cafeteria_bluemeth.ui.viewmodels.OrderViewModel
import com.finga.cafeteria_bluemeth.ui.viewmodels.UserViewModel
import com.finga.cafeteria_bluemeth.utils.Methods
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.time.LocalDate

class BillFragment : Fragment() {
    lateinit var listNewListDishAdapter: NewListDishAdapter
    private lateinit var orderViewModel: OrderViewModel
    private val billViewModel: BillViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var binding: FragmentBillBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_bill, container, false
        )
        setHasOptionsMenu(true)

        orderViewModel = ViewModelProvider(this)[OrderViewModel::class.java]

        setRecyclerView()
        observeDishes()

        binding.btnPagar.setOnClickListener() {
            pay()
        }

        return binding.root
    }

    private fun setRecyclerView() {
        val itemsRecyclerView = binding.RecyclerView
        itemsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        itemsRecyclerView.setHasFixedSize(true)
        listNewListDishAdapter = NewListDishAdapter()

       clickToDish()

        itemsRecyclerView.adapter = listNewListDishAdapter
    }

    private fun clickToDish() {
        listNewListDishAdapter.setItemListener(object : RecyclerClickListener {
            override fun onItemClick(position: Int) {
                val dishList = listNewListDishAdapter.currentList.toMutableList()
                setBottomSheetDialog(dishList[position])
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun observeDishes() {
        listNewListDishAdapter.submitList(billViewModel.getPlatsFromBill())
        binding.txtPreu.text = "${billViewModel.getPreu()}€"
    }

    private fun alertMessage(desc: String, btnMessage: String, intent: Intent?) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage(desc)
            .setCancelable(false)
            .setPositiveButton(btnMessage) { dialog, _ ->
                if(intent != null) {
                    startActivity(intent)
                }
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }

    fun setBottomSheetDialog(dish: Dish) {
        val dialog = BottomSheetDialog(requireContext())

        val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)

        val imgDish = view.findViewById<ImageView>(R.id.img_plat_sheet)
        val nameDish = view.findViewById<TextView>(R.id.txt_name_plat_sheet)
        val priceDish = view.findViewById<TextView>(R.id.txt_price_plat_sheet)

        nameDish.text = dish.name
        imgDish.setBackgroundResource(Methods.searchDishImage(dish.name))
        priceDish.text = "Precio: ${dish.price}€"

        val btnClose = view.findViewById<Button>(R.id.idBtnDismiss)

        btnClose.text = "Eliminar plato"

        btnClose.setOnClickListener {
            billViewModel.removeDishToBill(dish)
            dialog.dismiss()
        }

        dialog.setCancelable(true)
        dialog.setContentView(view)
        dialog.show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun pay() {
        if(userViewModel.userIsLogged()) {
            if(billViewModel.isFullOrder()) {
                addOrderToDB()

                alertMessage("Ha realizado la compra!", "SEGUIR COMPRANDO", intentToHome())
            } else {
                alertMessage("Debe pedir tres platos!", "CANCELAR", null)
            }

        } else {
            alertMessage("Para realizar estas acciones debe iniciar sesión", "INICIAR SESION", Intent(requireContext(), LoginActivity::class.java))
        }
    }

    private fun intentToHome(): Intent {
        return Intent(requireContext(), HomeActivity::class.java).apply {
            putExtra("user_nickname", userViewModel.getCurrentUser()!!.nickname)
            putExtra("user_email", userViewModel.getCurrentUser()!!.email)
            putExtra("user_password", userViewModel.getCurrentUser()!!.password)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun addOrderToDB() {
        val maxId = orderViewModel.getMaxId(requireContext())
        val currentDate = LocalDate.now()
        val order = Order(billViewModel.listPlats[0].name, billViewModel.listPlats[1].name, billViewModel.listPlats[2].name, currentDate.toString(), userViewModel.getCurrentUser()!!.email,  maxId + 1)

        orderViewModel.addOrder(requireContext(), order)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
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
                alertMessage("Para realizar estas acciones debe iniciar sesión", "INICIAR SESION", Intent(requireContext(), LoginActivity::class.java))
            }
        }

        return super.onOptionsItemSelected(item)
    }


}