package com.finga.cafeteria_bluemeth.ui.pages.my_profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.databinding.ActivityLoginBinding
import com.finga.cafeteria_bluemeth.databinding.ActivityMyProfileBinding
import com.finga.cafeteria_bluemeth.ui.pages.login.LoginActivity

class MyProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userEmail = intent.extras?.getString("user_nickname")

        binding.txtNickname.text = userEmail?.uppercase()
        binding.txtEmail.text = "EMAIL: ${intent.extras?.getString("user_email")}"

        binding.btnHistorial.setOnClickListener() {
            val intent = Intent(this, ShowOrdersActivity::class.java)
            intent.putExtra("user_email", userEmail)
            startActivity(intent)
        }
    }
}