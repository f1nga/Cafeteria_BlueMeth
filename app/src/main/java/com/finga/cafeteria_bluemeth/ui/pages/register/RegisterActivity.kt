package com.finga.cafeteria_bluemeth.ui.register

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.finga.cafeteria_bluemeth.databinding.ActivityRegisterBinding
import com.finga.cafeteria_bluemeth.data.models.User
import com.finga.cafeteria_bluemeth.ui.pages.login.LoginActivity
import com.finga.cafeteria_bluemeth.ui.viewmodels.UserViewModel


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var userViewModel: UserViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.btnRegister.setOnClickListener() {
            registerUserToDatabase()
        }

        binding.txtFinal.setOnClickListener() {
            goToLogin()
        }
    }

    private fun registerUserToDatabase() {
        val userNickname = binding.txtInputNickname.text.toString()
        val userEmail = binding.txtInputEmail.text.toString()
        val userPassword = binding.txtInputPassword.text.toString()

        if(userNickname == "" || userEmail == "" || userPassword == "") {
            emptyFieldsAlert()
        } else {
            userViewModel.addUser(this, User(userNickname, userEmail, userPassword))

            goToLogin()
        }
    }

    private fun emptyFieldsAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Los campos no pueden estar vacíos")
            .setCancelable(false)
            .setPositiveButton("TRY AGAIN") { dialog, _ ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}