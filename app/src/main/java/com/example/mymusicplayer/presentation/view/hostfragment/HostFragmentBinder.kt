package com.example.mymusicplayer.presentation.view.hostfragment


import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
                white(binding.iTunes)
                gray(binding.myMusic)
                gray(binding.library)
            }
            1 -> {
                white(binding.library)
                gray(binding.myMusic)
                gray(binding.iTunes)
            }
            2 -> {
                white(binding.myMusic)
                gray(binding.library)
                gray(binding.iTunes)
            }
            3 -> {
                gray(binding.myMusic)
                gray(binding.library)
                gray(binding.iTunes)
            }
        }
    }

    private fun gray(view: TextView) {
        view.setTextColor(Color.GRAY)
        view.textSize - 16F
        view.typeface = Typeface.DEFAULT
    }

    private fun white(view: TextView) {
        view.setTextColor(Color.WHITE)
        view.textSize - 18F
        view.typeface = Typeface.DEFAULT_BOLD
    }
}
