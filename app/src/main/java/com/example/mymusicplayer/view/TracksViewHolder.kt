package com.example.mymusicplayer.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymusicplayer.R

class TracksViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val imageTrack: ImageView = view.findViewById(R.id.imageTrack)
    val titleTrack: TextView = view.findViewById(R.id.titleTrack)
    val textTrack: TextView = view.findViewById(R.id.textTrack)
}