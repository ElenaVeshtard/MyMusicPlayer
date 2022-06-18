package com.example.mymusicplayer.view


import android.graphics.Color
import android.graphics.Typeface
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

    fun changeFragment(numberFragment: Int) {
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
            3 -> {
                binding.myMusic.setTextColor(Color.GRAY)
                binding.myMusic.textSize - 16F
                binding.myMusic.typeface = Typeface.DEFAULT
                binding.library.setTextColor(Color.GRAY)
                binding.library.textSize = 16F
                binding.library.typeface = Typeface.DEFAULT
                binding.iTunes.setTextColor(Color.GRAY)
                binding.iTunes.textSize = 16F
                binding.iTunes.typeface = Typeface.DEFAULT
            }
        }
    }
}
