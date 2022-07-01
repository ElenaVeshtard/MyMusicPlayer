package com.example.mymusicplayer.presentation.view.librarymusicfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.mymusicplayer.data.UploadMusicWorker
import com.example.mymusicplayer.data.utils.DataKeys.Companion.DEFAULT_ALBUM_ID
import com.example.mymusicplayer.data.utils.DataKeys.Companion.LOADING_TRACKS_URL
import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.MusicSourceType
import com.example.mymusicplayer.domain.TrackModel
import com.example.mymusicplayer.presentation.viewmodel.AlbumsViewModel
import com.example.mymusicplayer.presentation.viewmodel.TracksViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class LibraryMusicFragment : Fragment() {
    private lateinit var viewBinder: LibraryMusicFragmentBinder
    private val viewModel: AlbumsViewModel by inject()
    private val tracksLibraryViewModel: TracksViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinder = LibraryMusicFragmentBinder(this, ::onItemClick, ::onAlbumClick)

        return viewBinder.onCreateView(inflater, container, savedInstanceState)
    }

    private fun onAlbumClick(view: View, album: AlbumModel): Boolean {
        tracksLibraryViewModel.loadTracks(MusicSourceType.LIBRARY, album.id)
        return true
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
        viewModel.loadAlbums(MusicSourceType.LIBRARY)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateAlbums.collect {
                    if (it != null) {
                        val getIt = it.getOrThrow()
                        DEFAULT_ALBUM_ID = getIt[0].id
                        viewBinder.onDataLoaded(getIt)
                    }

                }
            }
        }

        tracksLibraryViewModel.loadTracks(MusicSourceType.LIBRARY, DEFAULT_ALBUM_ID)
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                tracksLibraryViewModel.stateAlbumsWithTracks.collect {
                    if (it != null) {
                        viewBinder.tracksLoaded(it.getOrThrow())
                    }
                }
            }
        }
    }
}