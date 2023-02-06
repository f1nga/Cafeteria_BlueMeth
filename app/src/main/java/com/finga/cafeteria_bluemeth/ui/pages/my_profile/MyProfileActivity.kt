package com.finga.cafeteria_bluemeth.ui.pages.my_profile

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.finga.cafeteria_bluemeth.data.models.Order
import com.finga.cafeteria_bluemeth.databinding.ActivityMyProfileBinding
import com.finga.cafeteria_bluemeth.ui.viewmodels.OrderViewModel

class MyProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyProfileBinding
    private lateinit var orderViewModel: OrderViewModel
    private var userEmail: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        orderViewModel = ViewModelProvider(this)[OrderViewModel::class.java]

        setData()

        binding.btnHistorial.setOnClickListener() {
            goToHistorial()
        }
    }

    private fun goToHistorial() {
        val intent = Intent(this, ShowOrdersActivity::class.java)
        intent.putExtra("user_email", userEmail)
        startActivity(intent)
    }

    @SuppressLint("SetTextI18n")
    private fun setData() {
        userEmail = intent.extras?.getString("user_nickname")

        binding.txtNickname.text = userEmail?.uppercase()
        binding.txtEmail.text = "EMAIL: ${intent.extras?.getString("user_email")}"

        val listOrders: List<Order> = orderViewModel.getOrdersByUser(this, userEmail!!)

        binding.txtPedidos.text = listOrders.size.toString()
    }
}