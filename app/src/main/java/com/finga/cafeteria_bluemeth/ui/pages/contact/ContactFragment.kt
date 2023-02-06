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
            sendMessage()
        }

        return binding.root
    }

    private fun sendMessage() {
        if(binding.inputFullname.text.toString() == "" || binding.inputEmailContact.text.toString() == "" || binding.inputMessage.text.toString() == "") {
            alertMessage("Los campos no pueden estar vacíos!", "TRY AGAIN", null)
        } else {
            alertMessage("El mensaje ha sido enviado!", "GO BACK", Intent(requireContext(), WelcomeActivity::class.java))
        }

    }

    private fun alertMessage(desc: String, btnMessage: String, intent: Intent?) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage(desc)
            .setCancelable(false)
            .setPositiveButton(btnMessage) { dialog, _ ->
                if(intent != null) {
                    startActivity(intent)
                }
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }
}