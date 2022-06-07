package com.example.mymusicplayer.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mymusicplayer.databinding.HostFragmentBinding

class HostFragmentBinder(val fragment: HostFragment) {

    lateinit var binding: HostFragmentBinding

    fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View {
        binding = HostFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }
}