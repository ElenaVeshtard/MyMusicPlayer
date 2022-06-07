package com.example.mymusicplayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.mymusicplayer.R

class PurchaseFragment : Fragment() {
    lateinit var viewBinder: PurchaseFragmentBinder

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinder = PurchaseFragmentBinder(this)

        return viewBinder.onCreateView(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (parentFragment as NavHostFragment).parentFragment?.view?.findViewById<View>(R.id.library)
            ?.setOnClickListener {
                val navController = findNavController()

                val action =
                   PurchaseFragmentDirections.actionPurchaseFragmentToLibraryMusicFragment()
                navController.navigate(action)
            }

        (parentFragment as NavHostFragment).parentFragment?.view?.findViewById<View>(R.id.iTunes)
            ?.setOnClickListener {
                val navController = findNavController()

                val action =
                   PurchaseFragmentDirections.actionPurchaseFragmentToITunesMusicFragment()
                navController.navigate(action)
            }
        (parentFragment as NavHostFragment).parentFragment?.view?.findViewById<View>(R.id.myMusic)
            ?.setOnClickListener {
                val navController = findNavController()

                val action =
                    PurchaseFragmentDirections.actionPurchaseFragmentToMyMusicFragment()
                navController.navigate(action)
            }

    }
}
