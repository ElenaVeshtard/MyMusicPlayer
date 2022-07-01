package com.example.mymusicplayer.presentation.view.itunesfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mymusicplayer.data.utils.DataKeys
import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.MusicSourceType
import com.example.mymusicplayer.presentation.viewmodel.AlbumsViewModel
import com.example.mymusicplayer.presentation.viewmodel.TracksViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class ITunesMusicFragment : Fragment() {

    private lateinit var viewBinder: ITunesMusicFragmentBinder
    private val albumsViewModel: AlbumsViewModel by inject()
    private val tracksITunesViewModel: TracksViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinder = ITunesMusicFragmentBinder(this, ::onAlbumClick)
        return viewBinder.onCreateView(inflater, container, savedInstanceState)
    }

    private fun onAlbumClick(view: View, album: AlbumModel): Boolean {
        tracksITunesViewModel.loadTracks(MusicSourceType.ITUNES, album.id)
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        albumsViewModel.loadAlbums(MusicSourceType.ITUNES)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                albumsViewModel.stateAlbums.collect {
                    if (it != null) {
                        val getIt = it.getOrThrow()
                        DataKeys.DEFAULT_ALBUM_ID = getIt[0].id
                        viewBinder.onDataLoaded(getIt)
                    }
                }
            }
        }
        tracksITunesViewModel.loadTracks(MusicSourceType.ITUNES, DataKeys.DEFAULT_ALBUM_ID)
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                tracksITunesViewModel.stateAlbumsWithTracks.collect {
                    if (it != null)
                        viewBinder.tracksLoaded(it.getOrThrow())
                }
            }
        }
    }
}

