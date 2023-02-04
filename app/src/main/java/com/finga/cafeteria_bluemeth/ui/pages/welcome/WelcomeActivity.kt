package com.finga.cafeteria_bluemeth.ui.pages.welcome

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.databinding.ActivityWelcomeBinding
import com.finga.cafeteria_bluemeth.ui.pages.faqs.FaqsActivity
import com.finga.cafeteria_bluemeth.ui.pages.home.HomeActivity
import com.finga.cafeteria_bluemeth.ui.pages.login.LoginActivity
import com.finga.cafeteria_bluemeth.ui.register.RegisterActivity
import java.time.LocalDate
import java.time.LocalDateTime

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = binding.drawerLayout
        val navController = this.findNavController(R.id.myNavHostFragment)


        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)

    }

   override fun onSupportNavigateUp(): Boolean {
       val navController = this.findNavController(R.id.myNavHostFragment)
       return NavigationUI.navigateUp(navController, drawerLayout)
   }
}