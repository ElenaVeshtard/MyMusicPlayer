package com.example.mymusicplayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.mymusicplayer.R
import com.example.mymusicplayer.data.UploadMusicWorker
import com.example.mymusicplayer.domain.TrackModel
import com.example.mymusicplayer.presentation.AlbumsViewModel
import com.example.mymusicplayer.presentation.FragmentStateViewModel
import com.example.mymusicplayer.presentation.TracksViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class LibraryMusicFragment : Fragment() {
    lateinit var viewBinder: LibraryMusicFragmentBinder
    private val fragmentStateViewModel: FragmentStateViewModel by inject()
    private val viewModel: AlbumsViewModel by inject()
    private val tracksLibraryViewModel: TracksViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinder = LibraryMusicFragmentBinder(this, ::onItemClick)

        return viewBinder.onCreateView(inflater, container, savedInstanceState)
    }


    fun onItemClick(view: View, tracks: TrackModel): Boolean {

        val data = Data.Builder()
        data.putString("1", tracks.url)

        val uploadWork =
            OneTimeWorkRequestBuilder<UploadMusicWorker>().setInputData(data.build()).build()
        WorkManager.getInstance(requireContext()).enqueue(uploadWork)
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        (parentFragment as NavHostFragment).parentFragment?.view?.findViewById<View>(R.id.imagePurchase)
            ?.setOnClickListener {
                val navController = findNavController()
                val action =
                    LibraryMusicFragmentDirections.actionLibraryMusicFragmentToPurchaseFragment()
                navController.navigate(action)
            }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                fragmentStateViewModel.appFragmentState.collect {
                    if (it != null) {
                        val navController = findNavController()
                        if (it.numberOfFragment == 0) {
                            val action =
                                LibraryMusicFragmentDirections.actionLibraryMusicFragmentToITunesMusicFragment()
                            navController.navigate(action)
                        }
                        if (it.numberOfFragment == 2) {
                            val action =
                                LibraryMusicFragmentDirections.actionLibraryMusicFragmentToMyMusicFragment()
                            navController.navigate(action)
                        }
                    }
                }
            }
        }

        viewModel.loadData(false)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateITunes.collect {
                    if (it != null)
                        viewBinder.onDataLoaded(it.getOrThrow())
                }
            }
        }

        tracksLibraryViewModel.loadTracks()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                tracksLibraryViewModel.stateITunes.collect {
                    if (it != null)
                        viewBinder.tracksLoaded(it.getOrThrow())
                }
            }
        }
    }
}