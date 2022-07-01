package com.example.mymusicplayer.presentation.view.librarymusicfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymusicplayer.databinding.LibraryMusicFragmentBinding
import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.AlbumsWithTracks
import com.example.mymusicplayer.domain.TrackModel
import com.example.mymusicplayer.presentation.view.albumsrecycler.AlbumsAdapter
import com.example.mymusicplayer.presentation.view.traksrecycler.TracksAdapter

class LibraryMusicFragmentBinder(
    val fragment: LibraryMusicFragment,
    private val onItemClick: (View, TrackModel) -> Boolean,
    private val onAlbumClick: (View, AlbumModel) -> Boolean
) {

    lateinit var binding: LibraryMusicFragmentBinding

    fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LibraryMusicFragmentBinding.inflate(inflater, container, false)

        var layoutManager =
            LinearLayoutManager(fragment.requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        binding.albumsRecyclerLibrary.layoutManager = layoutManager

        layoutManager =
            LinearLayoutManager(fragment.requireActivity())

        binding.tracksRecyclerAlbum.layoutManager = layoutManager

        return binding.root
    }

    fun onDataLoaded(list: List<AlbumModel>?) {

        if (list != null)
            if (binding.albumsRecyclerLibrary.adapter == null)
                binding.albumsRecyclerLibrary.adapter = AlbumsAdapter(list, onAlbumClick)
            else
                (binding.albumsRecyclerLibrary.adapter as AlbumsAdapter).data = list
    }

    fun tracksLoaded(list: AlbumsWithTracks?) {
        if (list != null) {
            binding.tracksRecyclerAlbum.adapter = TracksAdapter(
                list.tracks,
                onItemClick
            )
        }
    }
}