package com.finga.cafeteria_bluemeth.ui.pages.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.adapters.ViewPagerAdapter
import com.finga.cafeteria_bluemeth.ui.pages.home.tabs.BillFragment
import com.finga.cafeteria_bluemeth.ui.pages.home.tabs.FirstDishFragment
import com.finga.cafeteria_bluemeth.ui.pages.home.tabs.SecondDishFragment
import com.finga.cafeteria_bluemeth.ui.pages.home.tabs.ThirdDishFragment
import com.google.android.material.tabs.TabLayout


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(FirstDishFragment(), "1r PLATO")
        adapter.addFragment(SecondDishFragment(), "2ndo PLATO")
        adapter.addFragment(ThirdDishFragment(), "POSTRE")
        adapter.addFragment(BillFragment(), "PAGAR")

        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val tabs = findViewById<TabLayout>(R.id.tabs)

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }
}
