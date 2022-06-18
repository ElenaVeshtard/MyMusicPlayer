package com.example.mymusicplayer.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymusicplayer.databinding.PurchaseFragmentBinding
import com.example.mymusicplayer.domain.purchase.ProductEntity
import com.example.mymusicplayer.view.purchase.PurchaseAdapter


class PurchaseFragmentBinder(
    private val fragment: Fragment,
    private val onItemClick: (ProductEntity) -> Unit
) {

    private lateinit var binding: PurchaseFragmentBinding

    fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View {
        binding = PurchaseFragmentBinding.inflate(inflater, container, false)

        binding.purchasesContainer.layoutManager = LinearLayoutManager(fragment.requireContext())

        return binding.root
    }

    fun onDataLoaded(data: List<ProductEntity>) {

        if (binding.purchasesContainer.adapter == null) {
            binding.purchasesContainer.adapter = PurchaseAdapter(data, onItemClick)
        } else {
            (binding.purchasesContainer.adapter as PurchaseAdapter).data = data
        }
    }
}
