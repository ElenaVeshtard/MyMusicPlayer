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
import com.example.mymusicplayer.presentation.AlbumsViewModel
import com.example.mymusicplayer.presentation.FragmentStateViewModel
import com.example.mymusicplayer.presentation.TracksViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class ITunesMusicFragment : Fragment() {

    private lateinit var viewBinder: ITunesMusicFragmentBinder
    private val viewModel: AlbumsViewModel by inject()
    private val tracksITunesViewModel: TracksViewModel by inject()
    private val fragmentStateViewModel: FragmentStateViewModel by inject()

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
                            val action =
                                ITunesMusicFragmentDirections.actionITunesMusicFragmentToLibraryMusicFragment()
                            navController.navigate(action)
                        }
                        if (it.numberOfFragment == 2) {
                            val action =
                                ITunesMusicFragmentDirections.actionITunesMusicFragmentToMyMusicFragment()
                            navController.navigate(action)
                        }
                        if (it.numberOfFragment == 3) {
                            val action =
                                ITunesMusicFragmentDirections.actionITunesMusicFragmentToPurchaseFragment()
                            navController.navigate(action)
                        }
                    }
                }
            }
        }
        viewModel.loadData(true)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateITunes.collect {
                    if (it != null)
                        viewBinder.onDataLoaded(it.getOrThrow())
                }
            }
        }

        tracksITunesViewModel.loadTracks()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                tracksITunesViewModel.stateITunes.collect {
                    if (it != null)
                        viewBinder.tracksLoaded(it.getOrThrow())
                }
            }
        }
    }
}

