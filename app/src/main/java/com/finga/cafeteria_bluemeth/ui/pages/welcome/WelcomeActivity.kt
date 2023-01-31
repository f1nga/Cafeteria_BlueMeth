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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnToPlats.setOnClickListener() {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("user_email", "" )
            intent.putExtra("user_password", "" )
            intent.putExtra("user_nickname", "" )
            startActivity(intent)
        }

        binding.btnToLogin.setOnClickListener() {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.txtFinal.setOnClickListener() {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }
}