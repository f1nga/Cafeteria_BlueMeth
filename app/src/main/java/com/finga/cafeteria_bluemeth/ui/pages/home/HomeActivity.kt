package com.finga.cafeteria_bluemeth.ui.pages.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.ui.adapters.ViewPagerAdapter
import com.finga.cafeteria_bluemeth.ui.pages.home.tabs.*
import com.google.android.material.tabs.TabLayout

class HomeActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val userEmail = intent.extras?.getString("user_email")
        val userPassword = intent.extras?.getString("user_password")
        val userNickname = intent.extras?.getString("user_nickname")

        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(FirstDishFragment(userEmail!!, userPassword!!, userNickname!!), "1r PLATO")
        adapter.addFragment(SecondDishFragment(), "2ndo PLATO")
        adapter.addFragment(ThirdDishFragment(), "POSTRE")
        adapter.addFragment(BillFragment(), "PAGAR")

        viewPager = findViewById(R.id.viewPager)
        val tabs = findViewById<TabLayout>(R.id.tabs)

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

    }
}