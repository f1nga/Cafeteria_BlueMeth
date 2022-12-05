package com.finga.cafeteria_bluemeth.ui.pages.home.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.databinding.FragmentThirdDishBinding

class BillFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentThirdDishBinding>(
            inflater,
            R.layout.fragment_third_dish, container, false
        )
        setHasOptionsMenu(true)
        return binding.root
    }


}