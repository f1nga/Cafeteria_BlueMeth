package com.finga.cafeteria_bluemeth.ui.pages.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.finga.cafeteria_bluemeth.data.models.User
import com.finga.cafeteria_bluemeth.databinding.ActivityLoginBinding
import com.finga.cafeteria_bluemeth.ui.pages.home.HomeActivity
import com.finga.cafeteria_bluemeth.ui.register.RegisterActivity
import com.finga.cafeteria_bluemeth.ui.viewmodels.UserViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.btnLogin.setOnClickListener() {
            loginToDatabase()
        }

        binding.txtFinal.setOnClickListener() {
            goToRegister()
        }
    }

    private fun loginToDatabase() {
        val userEmail = binding.inputEmailText.text.toString()
        val userPassword = binding.inputPasswordText.text.toString()

        if(userEmail == "" || userPassword == "") {
            alertMessage("Los campos no pueden estar vacíos")
        } else {
            userViewModel.login(this, userEmail, userPassword).observe(this) {
                if(it == null) {
                    alertMessage("No existe este usuario")
                } else {
                    goToHome(it)
                }
            }
        }
    }

    private fun alertMessage(desc: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(desc)
            .setCancelable(false)
            .setPositiveButton("TRY AGAIN") { dialog, _ ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }

    private fun goToHome(user: User) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("user_email", user.email )
        intent.putExtra("user_password", user.password )
        intent.putExtra("user_nickname", user.nickname )
        startActivity(intent)
        finish()
    }

    private fun goToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}
