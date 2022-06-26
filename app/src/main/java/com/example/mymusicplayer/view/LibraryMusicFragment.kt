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
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.mymusicplayer.R
import com.example.mymusicplayer.data.UploadMusicWorker
import com.example.mymusicplayer.data.utils.DataKeys.Companion.LOADING_TRACKS_URL
import com.example.mymusicplayer.domain.TrackModel
import com.example.mymusicplayer.presentation.AlbumsViewModel
import com.example.mymusicplayer.presentation.FragmentStateViewModel
import com.example.mymusicplayer.presentation.TracksViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LibraryMusicFragment : Fragment() {
    private lateinit var viewBinder: LibraryMusicFragmentBinder
    private val fragmentStateViewModel: FragmentStateViewModel by sharedViewModel()
    private val viewModel: AlbumsViewModel by sharedViewModel()
    private val tracksLibraryViewModel: TracksViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinder = LibraryMusicFragmentBinder(this, ::onItemClick)

        return viewBinder.onCreateView(inflater, container, savedInstanceState)
    }

    private fun onItemClick(view: View, tracks: TrackModel): Boolean {

        val data = Data.Builder()
        data.putString(LOADING_TRACKS_URL, tracks.url)

        val uploadWork =
            OneTimeWorkRequestBuilder<UploadMusicWorker>().setInputData(data.build()).build()
        WorkManager.getInstance(requireContext()).enqueue(uploadWork)
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                fragmentStateViewModel.appFragmentState.collect {
                    if (it != null) {
                        val navController = findNavController()
                        if (it.numberOfFragment == 0) {
                            navController.navigate(R.id.action_libraryMusicFragment_to_ITunesMusicFragment)
                        }
                        if (it.numberOfFragment == 2) {
                            navController.navigate(R.id.action_libraryMusicFragment_to_myMusicFragment)
                        }
                        if (it.numberOfFragment == 3) {
                            navController.navigate(R.id.action_libraryMusicFragment_to_purchaseFragment)
                        }
                    }
                }
            }
        }

        viewModel.loadAlbums()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateAlbums.collect {
                    if (it != null)
                        viewBinder.onDataLoaded(it.getOrThrow())
                }
            }
        }

        tracksLibraryViewModel.loadTracks()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                tracksLibraryViewModel.stateTracks.collect {
                    if (it != null)
                        viewBinder.tracksLoaded(it.getOrThrow())
                }
            }
        }
    }
}