package com.example.mymusicplayer.view.albumsrecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mymusicplayer.R
import com.example.mymusicplayer.data.utils.ImageUtils
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
            ImageUtils.setRoundRect(this, 10)
        }

        holder.textAlbum.text = item.name
        holder.albumImageFrame.apply {
            setBackgroundResource(R.color.colorPrimary)
            ImageUtils.setRoundRect(this, 12)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

