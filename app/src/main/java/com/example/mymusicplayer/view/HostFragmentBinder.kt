package com.example.mymusicplayer.view

import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.mymusicplayer.R
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

    fun changeFragment(numberFragment: Int) {
        Log.i("test", numberFragment.toString())
        when (numberFragment) {
            0 -> {
                binding.iTunes.setTextColor(Color.WHITE)
                binding.iTunes.textSize = 18F
                binding.iTunes.typeface = Typeface.DEFAULT_BOLD
                binding.library.setTextColor(Color.GRAY)
                binding.library.textSize = 16F
                binding.library.typeface = Typeface.DEFAULT
                binding.myMusic.setTextColor(Color.GRAY)
                binding.myMusic.textSize - 16F
                binding.myMusic.typeface = Typeface.DEFAULT
            }
            1 -> {
                binding.library.setTextColor(Color.WHITE)
                binding.library.textSize = 18F
                binding.library.typeface = Typeface.DEFAULT_BOLD
                binding.iTunes.setTextColor(Color.GRAY)
                binding.iTunes.textSize = 16F
                binding.iTunes.typeface = Typeface.DEFAULT
                binding.myMusic.setTextColor(Color.GRAY)
                binding.myMusic.textSize - 16F
                binding.myMusic.typeface = Typeface.DEFAULT
            }
            2 -> {
                binding.myMusic.setTextColor(Color.WHITE)
                binding.myMusic.textSize - 18F
                binding.myMusic.typeface = Typeface.DEFAULT_BOLD
                binding.library.setTextColor(Color.GRAY)
                binding.library.textSize = 16F
                binding.library.typeface = Typeface.DEFAULT
                binding.iTunes.setTextColor(Color.GRAY)
                binding.iTunes.textSize = 16F
                binding.iTunes.typeface = Typeface.DEFAULT
            }
        }
    }/* private fun changeCurrentSelection(primary: TextView, secondary: TextView, three: TextView) {
        primary.typeface = Typeface.DEFAULT_BOLD
        primary.textSize = 18f
        primary.setTextColor(ContextCompat.getColor(requireContext(), R.color.selectedTextColor))
        primary.isClickable = false

        secondary.typeface = Typeface.create("sans-serif-light", Typeface.NORMAL)
        secondary.textSize = 16f
        secondary.setTextColor(ContextCompat.getColor(requireContext(), R.color.textColor))
        secondary.isClickable = true

        three.typeface = Typeface.create("sans-serif-light", Typeface.NORMAL)
        three.textSize = 16f
        three.setTextColor(ContextCompat.getColor(requireContext(), R.color.textColor))
        three.isClickable = true
    }*/

}