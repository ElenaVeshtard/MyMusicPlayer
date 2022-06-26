package com.example.mymusicplayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.mymusicplayer.R
import com.example.mymusicplayer.data.permission.PermissionState
import com.example.mymusicplayer.data.permission.StoragePermissionChecker
import com.example.mymusicplayer.presentation.FragmentStateViewModel
import com.example.mymusicplayer.presentation.MyAlbumsViewModel
import com.example.mymusicplayer.presentation.MyTracksViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.parameter.parametersOf


class MyMusicFragment : Fragment() {
    private lateinit var myMusicFragmentBinder: MyMusicFragmentBinder
    private val fragmentStateViewModel: FragmentStateViewModel by inject ()
    private val storagePermissionChecker: StoragePermissionChecker by inject {
        parametersOf(
            requireActivity()
        )
    }
    private val albumsViewModel: MyAlbumsViewModel by sharedViewModel()
    private val tracksViewModel: MyTracksViewModel by sharedViewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
                            navController.navigate(R.id.action_myMusicFragment_to_ITunesMusicFragment)
                        }
                        if (it.numberOfFragment == 1) {
                            navController.navigate(R.id.action_myMusicFragment_to_libraryMusicFragment)
                        }
                        if (it.numberOfFragment == 3) {
                            navController.navigate(R.id.action_myMusicFragment_to_purchaseFragment)
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
                    }
                }
            }
        }
    }

    private suspend fun loadAndObserveData() {
        albumsViewModel.loadAlbums()
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                albumsViewModel.stateMyAlbums.collect {
                    if (it != null)
                        myMusicFragmentBinder.onAlbumsLoaded(it.getOrThrow())
                    (view?.parent as? ViewGroup)?.doOnPreDraw {
                        startPostponedEnterTransition()
                    }
                }
            }
        }

        tracksViewModel.loadTracks()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                tracksViewModel.myStateTracks.collect {
                    if (it != null)
                        myMusicFragmentBinder.onTracksLoaded(it.getOrThrow())
                    (view?.parent as? ViewGroup)?.doOnPreDraw {
                        startPostponedEnterTransition()
                    }
                }
            }
        }
    }
}

