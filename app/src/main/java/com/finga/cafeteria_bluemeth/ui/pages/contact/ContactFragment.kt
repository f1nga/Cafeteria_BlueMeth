package com.finga.cafeteria_bluemeth.ui.pages.contact

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.databinding.FragmentContactBinding
import com.finga.cafeteria_bluemeth.databinding.FragmentFirstDishBinding
import com.finga.cafeteria_bluemeth.ui.pages.login.LoginActivity
import com.finga.cafeteria_bluemeth.ui.pages.welcome.WelcomeActivity


class ContactFragment : Fragment() {
    lateinit var binding: FragmentContactBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_contact, container, false
        )
        setHasOptionsMenu(true)

        binding.btnSend.setOnClickListener() {
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("El mensaje ha sido enviado!")
                .setCancelable(false)
                .setPositiveButton("VOLVER") { dialog, _ ->
                    val intent = Intent(requireContext(), WelcomeActivity::class.java)
                    startActivity(intent)
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
        }

        return binding.root
    }
}