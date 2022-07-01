package com.example.mymusicplayer.presentation.view.mymusicfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mymusicplayer.data.permission.PermissionState
import com.example.mymusicplayer.data.permission.StoragePermissionChecker
import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.presentation.viewmodel.MyAlbumsViewModel
import com.example.mymusicplayer.presentation.viewmodel.MyTracksViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class MyMusicFragment : Fragment() {
    private lateinit var myMusicFragmentBinder: MyMusicFragmentBinder
    private val storagePermissionChecker: StoragePermissionChecker by inject {
        parametersOf(requireActivity())
    }
    private val albumsViewModel: MyAlbumsViewModel by inject()
    private val tracksViewModel: MyTracksViewModel by inject()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        myMusicFragmentBinder = MyMusicFragmentBinder(this, ::onAlbumClick)

        return myMusicFragmentBinder.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    private fun onAlbumClick(view: View, album: AlbumModel): Boolean {
        tracksViewModel.loadTracks()
        return true
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
                tracksViewModel.myStateAlbumsWithTracks.collect {
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

