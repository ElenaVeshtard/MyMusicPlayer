package com.example.mymusicplayer.view

import android.content.res.Resources
import android.graphics.Outline
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mymusicplayer.R
import com.example.mymusicplayer.domain.Tracks
import kotlin.reflect.KFunction2

class TracksAdapter(var data: List<Tracks>, val onItemClick: (View, Tracks) -> Boolean) :
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
            setRoundRect(this, 10)
        }
        holder.titleTrack.text = item.title
        holder.textTrack.text = item.artist
        holder.itemView.setOnLongClickListener { onItemClick(it, item) }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun setRoundRect(imageView: ImageView, dp: Int) {
        imageView.clipToOutline = true
        imageView.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                outline.setRoundRect(0, 0, view.width, view.height, dpToPx(dp))
            }
        }
    }

    private fun dpToPx(dp: Int): Float = dp * Resources.getSystem().displayMetrics.density
}