package com.example.mymusicplayer.presentation.view.itunesfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymusicplayer.databinding.ItunesMusicFragmentBinding
import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.AlbumsWithTracks
import com.example.mymusicplayer.domain.TrackModel
import com.example.mymusicplayer.presentation.view.albumsrecycler.AlbumsAdapter
import com.example.mymusicplayer.presentation.view.traksrecycler.TracksAdapter


class ITunesMusicFragmentBinder(
    val fragment: ITunesMusicFragment,
    private val onAlbumClick: (View, AlbumModel) -> Boolean
) {
    private lateinit var binding: ItunesMusicFragmentBinding
    fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ItunesMusicFragmentBinding.inflate(inflater, container, false)
        var layoutManager =
            LinearLayoutManager(fragment.requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.albumsRecyclerITunes.layoutManager = layoutManager
        layoutManager =
            LinearLayoutManager(fragment.requireActivity())
        binding.tracksRecyclerITunes.layoutManager = layoutManager
        return binding.root
    }

    fun onDataLoaded(list: List<AlbumModel>?) {
        if (list != null)
            if (binding.albumsRecyclerITunes.adapter == null)
                binding.albumsRecyclerITunes.adapter = AlbumsAdapter(list, onAlbumClick)
            else
                (binding.albumsRecyclerITunes.adapter as AlbumsAdapter).data = list
    }

    private fun onItemClick(view: View, tracks: TrackModel): Boolean {
        return true
    }

    fun tracksLoaded(list: AlbumsWithTracks?) {
        if (list != null) {
            binding.tracksRecyclerITunes.adapter = TracksAdapter(list.tracks, ::onItemClick)
        }
    }
}