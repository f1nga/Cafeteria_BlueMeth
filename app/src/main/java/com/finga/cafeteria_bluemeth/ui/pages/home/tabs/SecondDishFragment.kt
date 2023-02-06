package com.finga.cafeteria_bluemeth.ui.pages.home.tabs

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.ui.adapters.ListDishAdapter
import com.finga.cafeteria_bluemeth.databinding.FragmentSecondFishBinding
import com.finga.cafeteria_bluemeth.data.models.Dish
import com.finga.cafeteria_bluemeth.ui.pages.faqs.FaqsActivity
import com.finga.cafeteria_bluemeth.ui.pages.login.LoginActivity
import com.finga.cafeteria_bluemeth.ui.pages.my_profile.MyProfileActivity
import com.finga.cafeteria_bluemeth.ui.viewmodels.BillViewModel
import com.finga.cafeteria_bluemeth.ui.viewmodels.DishViewModel
import com.finga.cafeteria_bluemeth.ui.viewmodels.UserViewModel
import com.finga.cafeteria_bluemeth.utils.Methods
import com.google.android.material.bottomsheet.BottomSheetDialog

class SecondDishFragment() : Fragment() {
    private val dishViewModel: DishViewModel by activityViewModels()
    private val billViewModel: BillViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var listDishAdapter: ListDishAdapter
    lateinit var binding: FragmentSecondFishBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_second_fish, container, false
        )
        setHasOptionsMenu(true)

        setRecyclerView()

        return binding.root
    }

    private fun setRecyclerView() {
        dishViewModel.getDishesByCategory(requireContext(), 2).observe(viewLifecycleOwner) {
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
                    if(!billViewModel.isFullOrder()) {
                        setBottomSheetDialog(plat)
                    } else {
                        alertMessage("No puedes elegir mas de 3 platos!", "CANCELAR", null)
                    }
                } else {
                    alertMessage("Para realizar estas acciones debe iniciar sesión", "INICIAR SESION", Intent(requireContext(), LoginActivity::class.java))
                }
            }
        })
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

    @SuppressLint("SetTextI18n")
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

        btnClose.setOnClickListener {
            billViewModel.addDishToBill(dish)
            dialog.dismiss()
        }

        dialog.setCancelable(true)
        dialog.setContentView(view)
        dialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_faqs){
            val intent = Intent(requireContext(), FaqsActivity::class.java)
            startActivity(intent)
        }

        if (id == R.id.action_my_profile){
            if(userViewModel.userIsLogged() ) {
                goToMyProfile()
            } else {
                alertMessage("Para realizar estas acciones debe iniciar sesión", "INICIAR SESION", Intent(requireContext(), LoginActivity::class.java))
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun goToMyProfile() {
        val intent = Intent(requireContext(), MyProfileActivity::class.java)
        intent.putExtra("user_nickname", userViewModel.getCurrentUser()?.nickname)
        intent.putExtra("user_email", userViewModel.getCurrentUser()?.email)
        startActivity(intent)
    }
}