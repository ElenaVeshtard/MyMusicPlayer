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
import com.example.mymusicplayer.presentation.AlbumsViewModel
import com.example.mymusicplayer.presentation.FragmentStateViewModel
import com.example.mymusicplayer.presentation.TracksViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ITunesMusicFragment : Fragment() {

    private lateinit var viewBinder: ITunesMusicFragmentBinder
    private val albumsViewModel: AlbumsViewModel by sharedViewModel()
    private val tracksITunesViewModel: TracksViewModel by sharedViewModel()
    private val fragmentStateViewModel: FragmentStateViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinder = ITunesMusicFragmentBinder(this)
        return viewBinder.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                fragmentStateViewModel.appFragmentState.collect {
                    if (it != null) {
                        val navController = findNavController()
                        if (it.numberOfFragment == 1) {
                            navController.navigate(R.id.action_ITunesMusicFragment_to_libraryMusicFragment)
                        }
                        if (it.numberOfFragment == 2) {
                            navController.navigate(R.id.action_ITunesMusicFragment_to_myMusicFragment)
                        }
                        if (it.numberOfFragment == 3) {
                            navController.navigate(R.id.action_ITunesMusicFragment_to_purchaseFragment)
                        }
                    }
                }
            }
        }
        albumsViewModel.loadAlbums()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                albumsViewModel.stateAlbums.collect {
                    if (it != null)
                        viewBinder.onDataLoaded(it.getOrThrow())
                }
            }
        }

        tracksITunesViewModel.loadTracks()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                tracksITunesViewModel.stateTracks.collect {
                    if (it != null)
                        viewBinder.tracksLoaded(it.getOrThrow())
                }
            }
        }
    }
}

