package com.example.mymusicplayer.presentation.view.purchasefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mymusicplayer.domain.purchase.PurchaseMakeInteractor
import com.example.mymusicplayer.presentation.viewmodel.FragmentStateViewModel
import com.example.mymusicplayer.presentation.viewmodel.PurchaseViewModel
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

        viewBinder = PurchaseFragmentBinder(this) {
            lifecycleScope.launch {
                purchaseMake.makePurchase(it)
            }
        }
        return viewBinder.onCreateView(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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


