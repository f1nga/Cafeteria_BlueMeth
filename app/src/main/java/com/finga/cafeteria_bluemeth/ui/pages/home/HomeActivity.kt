package com.finga.cafeteria_bluemeth.ui.pages.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.viewpager.widget.ViewPager
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.adapters.ViewPagerAdapter
import com.finga.cafeteria_bluemeth.models.Dish
import com.finga.cafeteria_bluemeth.ui.pages.home.tabs.*
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout


class HomeActivity : AppCompatActivity(), SendDish {
    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(FirstDishFragment(), "1r PLATO")
        adapter.addFragment(SecondDishFragment(), "2ndo PLATO")
        adapter.addFragment(ThirdDishFragment(), "POSTRE")
        adapter.addFragment(BillFragment(), "PAGAR")

        viewPager = findViewById(R.id.viewPager)
        val tabs = findViewById<TabLayout>(R.id.tabs)

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

    }

    override fun sendDataToBillFragment(plat: Dish?) {
        val tag = "android:switcher:" + R.id.viewPager.toString() + ":" + 3

        (supportFragmentManager.findFragmentByTag(tag) as? BillFragment)?.displayReceivedData(plat!!)
        var currentItem = getItem(+1)
        //viewPager.currentItem = currentItem
    }

    fun getItem(i: Int) = viewPager.currentItem + i


}