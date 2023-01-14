package com.finga.cafeteria_bluemeth.ui.pages.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.finga.cafeteria_bluemeth.data.DataUsers
import com.finga.cafeteria_bluemeth.databinding.ActivityLoginBinding
import com.finga.cafeteria_bluemeth.models.User
import com.finga.cafeteria_bluemeth.ui.pages.home.HomeActivity
import com.finga.cafeteria_bluemeth.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var btnLogin = binding.btnLogin

        btnLogin.setOnClickListener() {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        var txtFinal = binding.txtFinal

        txtFinal.setOnClickListener() {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}