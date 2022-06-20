package com.example.mymusicplayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.mymusicplayer.domain.purchase.PurchaseStateInteractor
import com.example.mymusicplayer.presentation.FragmentStateViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class HostFragment : Fragment() {

    private lateinit var viewBinder: HostFragmentBinder
    private val fragmentStateViewModel: FragmentStateViewModel by inject()
    private val purchaseStateInteractor: PurchaseStateInteractor by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinder =
            HostFragmentBinder(this)

        return viewBinder.onCreateView(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinder.binding.iTunes.setOnClickListener {
            fragmentStateViewModel.changeFragment(0)
        }

        viewBinder.binding.library.setOnClickListener {
            fragmentStateViewModel.changeFragment(1)
        }

        viewBinder.binding.myMusic.setOnClickListener {
            fragmentStateViewModel.changeFragment(2)
        }

        lifecycleScope.launch {

            purchaseStateInteractor.isPremium.collect {
                if (it) {
                    viewBinder.binding.imagePurchase.visibility = View.GONE

                } else {
                    viewBinder.binding.imagePurchase.visibility = View.VISIBLE

                    viewBinder.binding.imagePurchase.setOnClickListener {
                        fragmentStateViewModel.changeFragment(3)
                    }
                }
            }
        }

        lifecycleScope.launch {
            fragmentStateViewModel.appFragmentState.collect {
                if (it != null)
                    viewBinder.changeFragment(it.numberOfFragment)
            }
        }
    }
}


