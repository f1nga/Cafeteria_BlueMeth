package com.finga.cafeteria_bluemeth.ui.pages.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.databinding.ActivityLoginBinding
import com.finga.cafeteria_bluemeth.ui.pages.home.HomeActivity
import com.finga.cafeteria_bluemeth.ui.pages.home.tabs.FirstDishFragment
import com.finga.cafeteria_bluemeth.ui.register.RegisterActivity
import com.finga.cafeteria_bluemeth.viewmodel.UserViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var userViewModel: UserViewModel
    lateinit var userEmail: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        var btnLogin = binding.btnLogin

        btnLogin.setOnClickListener() {
             userEmail = binding.inputEmailText.text.toString()

            userViewModel.login(this, userEmail).observe(this) {
                if(it == null) {
                    val builder = AlertDialog.Builder(this)
                    builder.setMessage("No existe este usuario")
                        .setCancelable(false)
                        .setPositiveButton("TRY AGAIN") { dialog, _ ->

                            dialog.dismiss()
                        }


                    val alert = builder.create()
                    alert.show()
                } else {
                    Log.i("USER", it.email)

                    //userViewModel.setCurrentUser(it)
                }
            }


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
