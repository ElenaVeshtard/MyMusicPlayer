package com.example.mymusicplayer.view.albumsrecycler

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
import com.example.mymusicplayer.domain.AlbumModel


class AlbumsAdapter(var data: List<AlbumModel>) : RecyclerView.Adapter<AlbumsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)

        return AlbumsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        val item = data[position]
        holder.imageAlbum.apply {
            load(item.image)
            scaleType = ImageView.ScaleType.CENTER_CROP
            setRoundRect(this, 10)
        }

        holder.textAlbum.text = item.name
        holder.albumImageFrame.apply {
            setBackgroundResource(R.color.colorPrimary)
            setRoundRect(this, 12)
        }
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

