package com.finga.cafeteria_bluemeth.ui.pages.faqs

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.adapters.ListDishAdapter
import com.finga.cafeteria_bluemeth.adapters.ListFaqsAdapter
import com.finga.cafeteria_bluemeth.data.FaqsProvider
import com.finga.cafeteria_bluemeth.models.Dish
import com.finga.cafeteria_bluemeth.models.Faq

class FaqsActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faqs)

        val faqsAdapter = ListFaqsAdapter(FaqsProvider.getFaqs())

        val recyclerView = findViewById<RecyclerView>(R.id.faqs_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = faqsAdapter

        faqsAdapter.setOnItemClickListener(object: ListFaqsAdapter.onItemClickListener {
            override fun onItemClick(plat: Dish) {

            }

        })



    }
}