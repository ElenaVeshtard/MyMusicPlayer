package com.example.mymusicplayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymusicplayer.databinding.LibraryMusicFragmentBinding
import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.Tracks
import com.example.mymusicplayer.domain.TracksModel
import kotlin.reflect.KFunction2

class LibraryMusicFragmentBinder(
    val fragment: LibraryMusicFragment,
    val onItemClick: (View, Tracks) -> Boolean
) {

    private lateinit var binding: LibraryMusicFragmentBinding

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
                binding.albumsRecyclerLibrary.adapter = AlbumsAdapter(list)
            else
                (binding.albumsRecyclerLibrary.adapter as AlbumsAdapter).data = list
    }

    fun tracksLoaded(list: TracksModel?) {

        if (list != null) {
            if (binding.tracksRecyclerAlbum.adapter == null)
                binding.tracksRecyclerAlbum.adapter = TracksAdapter(list.tracks,
                    onItemClick)
            else
                (binding.tracksRecyclerAlbum.adapter as TracksAdapter).data = list.tracks
        }
    }
}