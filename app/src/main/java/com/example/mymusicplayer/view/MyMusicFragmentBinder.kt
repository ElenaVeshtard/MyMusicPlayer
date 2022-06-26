package com.example.mymusicplayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymusicplayer.databinding.MyMusicFragmentBinding
import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.TrackModel
import com.example.mymusicplayer.domain.Tracks
import com.example.mymusicplayer.view.albumsrecycler.AlbumsAdapter
import com.example.mymusicplayer.view.traksrecycler.TracksAdapter


class MyMusicFragmentBinder(val fragment: Fragment) {

    private lateinit var binding: MyMusicFragmentBinding


    fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MyMusicFragmentBinding.inflate(inflater, container, false)

        var layoutManager =
            LinearLayoutManager(fragment.requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        binding.albumsMusicRecyclerMyMusic.layoutManager = layoutManager
        layoutManager =
            LinearLayoutManager(fragment.requireActivity())

        binding.musicRecyclerMyMusic.layoutManager = layoutManager
        return binding.root
    }

    private fun onItemClick(view: View, item: TrackModel): Boolean {
        return false
    }

    fun onTracksLoaded(list: Tracks) {

        if (binding.musicRecyclerMyMusic.adapter == null) {
            binding.musicRecyclerMyMusic.adapter = TracksAdapter(list.tracks, ::onItemClick)
        } else {
            (binding.musicRecyclerMyMusic.adapter as TracksAdapter).data = list.tracks
        }
    }

    fun onAlbumsLoaded(list: List<AlbumModel>) {

        if (binding.albumsMusicRecyclerMyMusic.adapter == null) {
            binding.albumsMusicRecyclerMyMusic.adapter = AlbumsAdapter(list)
        } else {
            (binding.albumsMusicRecyclerMyMusic.adapter as AlbumsAdapter).data = list
        }
    }

}