package com.finga.cafeteria_bluemeth.ui.register

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.text.SpannableString
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.databinding.ActivityRegisterBinding
import com.finga.cafeteria_bluemeth.models.User
import com.finga.cafeteria_bluemeth.ui.pages.login.LoginActivity


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
   // private lateinit var database: AuthenticationRepository

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //database = AuthenticationRepository()

        /*binding.btnRegister.setOnClickListener() {
            val userEmail = binding.txtInputEmail.text.toString()
            val userPassword = binding.txtInputPassword.text.toString()

            database.register(User(userEmail, userPassword))
        }*/

        binding.txtFinal.setOnClickListener() {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}