package com.example.mymusicplayer.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.mymusicplayer.data.permission.PermissionState
import com.example.mymusicplayer.data.permission.StoragePermissionChecker
import com.example.mymusicplayer.data.permission.StoragePermissionCheckerImpl
import com.example.mymusicplayer.presentation.FragmentStateViewModel
import com.example.mymusicplayer.presentation.TracksMyMusicViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class MyMusicFragment : Fragment() {
    lateinit var myMusicFragmentBinder: MyMusicFragmentBinder
    private val fragmentStateViewModel: FragmentStateViewModel by inject()
    val storagePermissionChecker: StoragePermissionChecker by inject { parametersOf(requireActivity()) }
    private val viewModel: TracksMyMusicViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myMusicFragmentBinder = MyMusicFragmentBinder(this)

        return myMusicFragmentBinder.onCreateView(inflater, container, savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                fragmentStateViewModel.appFragmentState.collect {
                    if (it != null) {
                        val navController = findNavController()
                        if (it.numberOfFragment == 0) {
                            val action =
                                MyMusicFragmentDirections.actionMyMusicFragmentToITunesMusicFragment()
                            navController.navigate(action)
                        }
                        if (it.numberOfFragment == 1) {
                            val action =
                                MyMusicFragmentDirections.actionMyMusicFragmentToLibraryMusicFragment()
                            navController.navigate(action)
                        }
                    }
                }
            }
        }


        viewLifecycleOwner.lifecycleScope.launch {
            storagePermissionChecker.storagePermission.collectLatest { permissionState ->
                when (permissionState) {
                    PermissionState.HAS_PERMISSION -> {
                        loadAndObserveData()
                    }
                    PermissionState.NO_PERMISSION -> {
                        storagePermissionChecker.startPermissionDialog()
                    }
                    PermissionState.REJECTED_ASK_AGAIN -> {
                        storagePermissionChecker.startPermissionDialog()
                    }
                    PermissionState.REJECTED_FOREVER -> {
                        // nothing
                    }
                }
            }
        }
    }

    private suspend fun loadAndObserveData() {
        viewModel.loadTracks()

        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

            viewModel.stateITunes.collect {

                Log.i("mainActivity", "collectData $it")
                if (it != null) {
                    myMusicFragmentBinder.onDataLoaded(it)

                    (view?.parent as? ViewGroup)?.doOnPreDraw {
                        startPostponedEnterTransition()
                    }
                }
            }
        }
    }
}

