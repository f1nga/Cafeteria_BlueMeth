package com.finga.cafeteria_bluemeth.ui.pages.my_profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.finga.cafeteria_bluemeth.data.models.Order
import com.finga.cafeteria_bluemeth.databinding.ActivityShowOrdersBinding
import com.finga.cafeteria_bluemeth.ui.adapters.ListOrderAdapter
import com.finga.cafeteria_bluemeth.ui.viewmodels.OrderViewModel

class ShowOrdersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowOrdersBinding
    private lateinit var orderViewModel: OrderViewModel
    private lateinit var adapter: ListOrderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        orderViewModel = ViewModelProvider(this)[OrderViewModel::class.java]

        setRecyclerView()
        observeOrders()

    }

    private fun setRecyclerView() {
        val itemsRecyclerView = binding.RecyclerView
        itemsRecyclerView.layoutManager = LinearLayoutManager(this)
        itemsRecyclerView.setHasFixedSize(true)
        adapter = ListOrderAdapter()

        itemsRecyclerView.adapter = adapter
    }

    private fun observeOrders() {
        val userEmail = intent.extras?.getString("user_email")!!

        val listOrders: List<Order> = orderViewModel.getOrdersByUser2(this, userEmail)
        Log.i("HOOOOL", listOrders.toString())
        adapter.submitList(listOrders)
    }
}