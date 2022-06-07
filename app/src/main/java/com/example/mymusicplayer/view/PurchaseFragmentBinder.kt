package com.example.mymusicplayer.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mymusicplayer.databinding.PurchaseFragmentBinding


class PurchaseFragmentBinder(val fragment: PurchaseFragment) {

    lateinit var binding: PurchaseFragmentBinding

    fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View {
        binding = PurchaseFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }
}