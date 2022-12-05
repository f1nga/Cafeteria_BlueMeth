package com.finga.cafeteria_bluemeth.ui.pages.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.finga.cafeteria_bluemeth.adapters.ViewPagerAdapter
import com.finga.cafeteria_bluemeth.databinding.ActivityHomeBinding
import com.finga.cafeteria_bluemeth.ui.pages.home.tabs.BillFragment
import com.finga.cafeteria_bluemeth.ui.pages.home.tabs.FirstDishFragment
import com.finga.cafeteria_bluemeth.ui.pages.home.tabs.SecondDishFragment
import com.finga.cafeteria_bluemeth.ui.pages.home.tabs.ThirdDishFragment
import com.google.android.material.tabs.TabLayout


class HomeActivity : AppCompatActivity() {
    private lateinit var pager: ViewPager
    private lateinit var tab: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityHomeBinding>(
            this,
            com.finga.cafeteria_bluemeth.R.layout.activity_home
        )

        pager = binding.viewPager
        tab = binding.tabs

        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(FirstDishFragment(), "1r PLATO")
        adapter.addFragment(SecondDishFragment(), "2n PLATO")
        adapter.addFragment(ThirdDishFragment(), "3r PLATO")
        adapter.addFragment(BillFragment(), "PAGAR")

        pager.adapter = adapter

        tab.setupWithViewPager(pager)

        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab))

        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                Log.i("TextStats", "NEW TAB SELECTED: " + tab.position)
                pager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
    }

}
