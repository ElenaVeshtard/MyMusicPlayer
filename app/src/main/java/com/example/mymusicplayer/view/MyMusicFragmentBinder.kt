package com.example.mymusicplayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymusicplayer.databinding.MyMusicFragmentBinding
import com.example.mymusicplayer.domain.TrackModel
import com.example.mymusicplayer.view.traksrecycler.TracksAdapter


class MyMusicFragmentBinder(val fragment: Fragment) {

    private lateinit var binding: MyMusicFragmentBinding


    fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MyMusicFragmentBinding.inflate(inflater, container, false)

        val layoutManager =
            LinearLayoutManager(fragment.requireActivity())

        binding.musicRecyclerMyMusic.layoutManager = layoutManager

        return binding.root
    }

    private fun onItemClick(view: View, item: TrackModel): Boolean {
        return false
    }

    fun onDataLoaded(list: List<TrackModel>) {

        if (binding.musicRecyclerMyMusic.adapter == null) {
            binding.musicRecyclerMyMusic.adapter = TracksAdapter(list, ::onItemClick)
        } else {
            (binding.musicRecyclerMyMusic.adapter as TracksAdapter).data = list
        }
    }

}