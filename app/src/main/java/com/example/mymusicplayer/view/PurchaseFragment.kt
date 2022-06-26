package com.example.mymusicplayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.mymusicplayer.R
import com.example.mymusicplayer.domain.purchase.PurchaseMakeInteractor
import com.example.mymusicplayer.presentation.FragmentStateViewModel
import com.example.mymusicplayer.presentation.PurchaseViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PurchaseFragment : Fragment() {

    private lateinit var viewBinder: PurchaseFragmentBinder

    val fragmentStateViewModel: FragmentStateViewModel by sharedViewModel()
    private val viewModel: PurchaseViewModel by sharedViewModel()
    private val purchaseMake: PurchaseMakeInteractor by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewBinder = PurchaseFragmentBinder(this)
        {
            lifecycleScope.launch {
                purchaseMake.makePurchase(it)
            }
        }

        return viewBinder.onCreateView(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                fragmentStateViewModel.appFragmentState.collect {
                    if (it != null) {
                        val navController = findNavController()
                        if (it.numberOfFragment == 0) {
                            navController.navigate(R.id.action_purchaseFragment_to_ITunesMusicFragment)
                        }
                        if (it.numberOfFragment == 2) {
                            navController.navigate(R.id.action_purchaseFragment_to_myMusicFragment)
                        }
                        if (it.numberOfFragment == 1) {
                            navController.navigate(R.id.action_purchaseFragment_to_libraryMusicFragment)
                        }
                    }
                }
            }
        }

        viewModel.loadData()
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    if (it != null) {
                        if (it.isSuccess) {
                            viewBinder.onDataLoaded(it.getOrThrow())
                        }
                    }
                }
            }
        }
    }
}


