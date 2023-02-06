package com.finga.cafeteria_bluemeth.ui.pages.welcome

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.databinding.FragmentWelcomeBinding
import com.finga.cafeteria_bluemeth.ui.pages.home.HomeActivity
import com.finga.cafeteria_bluemeth.ui.pages.login.LoginActivity
import com.finga.cafeteria_bluemeth.ui.register.RegisterActivity

class WelcomeFragment : Fragment() {
    lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_welcome, container, false
        )
        setHasOptionsMenu(true)

        binding.btnToPlats.setOnClickListener {
            exploreWithoutLogin()
        }

        binding.btnToLogin.setOnClickListener {
           goToLogin()
        }

        binding.txtFinal.setOnClickListener {
            goToRegister()
        }

        return binding.root
    }

    private fun exploreWithoutLogin() {
        val intent = Intent(requireContext(), HomeActivity::class.java)
        intent.putExtra("user_email", "" )
        intent.putExtra("user_password", "" )
        intent.putExtra("user_nickname", "" )
        startActivity(intent)

        activity?.finish()
    }

    private fun goToLogin() {
        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    private fun goToRegister() {
        val intent = Intent(requireContext(), RegisterActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.
        onNavDestinationSelected(item,requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}