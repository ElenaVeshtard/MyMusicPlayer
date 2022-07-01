package com.example.mymusicplayer.presentation.view.traksrecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mymusicplayer.R
import com.example.mymusicplayer.data.utils.ImageUtils
import com.example.mymusicplayer.domain.TrackModel

class TracksAdapter(var data: List<TrackModel>, val onItemClick: (View, TrackModel) -> Boolean) :
    RecyclerView.Adapter<TracksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TracksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_track, parent, false)
        return TracksViewHolder(view)
    }

    override fun onBindViewHolder(holder: TracksViewHolder, position: Int) {
        val item = data[position]

        holder.imageTrack.apply {
            load(
                item.image.replace
                    ("{w}", "200").replace("{h}", "200")
            )
            ImageUtils.setRoundRect(this, 10)
        }
        holder.titleTrack.text = item.title
        holder.textTrack.text = item.artist
        holder.itemView.apply { setOnLongClickListener { onItemClick(it, item) } }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}